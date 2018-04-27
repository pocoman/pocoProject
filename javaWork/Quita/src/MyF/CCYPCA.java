package MyF;

public class CCYPCA {

    /*-------- 외부에서 접근할 수 있는 유일한 메서드 --------------*/
	public static void HH_Eigen(double [][] vector, double [] value, final double [][] A) {

		double [][] s = new double [A.length+1][A.length+1];
		double [] sp = new double [A.length+1];
		double [] e = new double [A.length+1];
		
		int n, i, j;
		n = A.length;
		
		for(i=0; i<n; i++) {
			for(j=0; j<n; j++) {
				s[i+1][j+1] = A[i][j];
			}
		}
		
		_HH_tred2(s, n, sp, e);
		_HH_tqli(sp, e, n, s);
		
		for(i=0; i<n; i++) {
			for(j=0; j<n; j++) {
				vector[i][j] = s[i+1][j+1];
			}
			value[i] = sp[i+1];
		}			
	}


	private static double _HH_pythag(double a, double b)
	{
		double absa, absb;
		absa = Math.abs(a);
		absb = Math.abs(b);
		
		if(absa > absb) return absa * Math.sqrt(1.0 + Math.pow(absb/absa,2));
		else return (absb==0.0?0.0:absb * Math.sqrt(1.0 + Math.pow(absa/absb,2)));
	}


    private static double SIGN(double a, double b) {
		return (b<0 ? -Math.abs(a) : Math.abs(a));
	}

	private static void _HH_tqli(double []d, double []e, int n, double [][]z)
	{
		int m,l,iter,i,k;
		double s,r,p,g,f,dd,c,b;
		for (i=2;i<=n;i++) e[i-1]=e[i]; // Convenient to renumber the elements of e.
		e[n]=0.0;
		for (l=1;l<=n;l++)
		{
			iter=0;
			do
			{
				for (m=l;m<=n-1;m++)
				{
					//Look for a single small subdiagonal element to split the matrix.
					dd=Math.abs(d[m])+Math.abs(d[m+1]);
					if ((Math.abs(e[m])+dd) == dd) break;
				}
				if (m != l)
				{
					//if (iter++ == 30) ;//nrerror("Too many iterations in tqli");
					iter++;
					g=(d[l+1]-d[l])/(2.0*e[l]); //Form shift.
					r=_HH_pythag(g,1.0);
					g=d[m]-d[l]+e[l]/(g+SIGN(r,g)); //This is dm . ks.
					s=c=1.0;
					p=0.0;
					for (i=m-1;i>=l;i--)
					{
						//QL, followed by Givens rotations to restore tridiagonal form.
						f=s*e[i];
						b=c*e[i];
						e[i+1]=(r=_HH_pythag(f,g));
						if (r == 0.0)
						{
							//Recover from underflow.
							d[i+1] -= p;
							e[m]=0.0;
							break;
						}
						s=f/r;
						c=g/r;
						g=d[i+1]-p;
						r=(d[i]-g)*s+2.0*c*b;
						d[i+1]=g+(p=s*r);
						g=c*r-b;
						/* Next loop can be omitted if eigenvectors not wanted*/
						for (k=1;k<=n;k++)
						{
							//Form eigenvectors.
							f=z[k][i+1];
							z[k][i+1]=s*z[k][i]+c*f;
							z[k][i]=c*z[k][i]-s*f;
						}
					}
					if (r == 0.0 && i >= l) continue;
					d[l] -= p;
					e[l]=g;
					e[m]=0.0;
				}
			} while (m != l);
		}
		
		
		
	}

	public static void _HH_tred2(double [][]a, int n, double []d, double []e)
	{
		int l,k,j,i;
		double scale,hh,h,g,f;
		for (i=n;i>=2;i--)
		{
			l=i-1;
			h=scale=0.0;
			if (l > 1)
			{
				for (k=1;k<=l;k++)
					scale += Math.abs(a[i][k]);
				if (scale == 0.0) //Skip transformation.
					e[i]=a[i][l];
				else
				{
					for (k=1;k<=l;k++)
					{
						a[i][k] /= scale; //Use scaled a for transformation.
						h += a[i][k]*a[i][k]; //Form s in h.
					}
					f=a[i][l];
					g=(f >= 0.0 ? -Math.sqrt(h) : Math.sqrt(h));
					e[i]=scale*g;
					h -= f*g; //Now h is equation (11.2.4).
					a[i][l]=f-g; //Store u in the ith row of a.
					f=0.0;
					for (j=1;j<=l;j++)
					{
						/* Next statement can be omitted if eigenvectors not wanted */
						a[j][i]=a[i][j]/h; //Store u/H in ith column of a.
						g=0.0; //Form an element of A ?u in g.
						for (k=1;k<=j;k++)
							g += a[j][k]*a[i][k];
						for (k=j+1;k<=l;k++)
							g += a[k][j]*a[i][k];
						e[j]=g/h; //Form element of p in temporarily unused element of e.
						f += e[j]*a[i][j];
					}
					hh=f/(h+h);
					for (j=1;j<=l;j++)
					{ //Form q and store in e overwriting p.
						f=a[i][j];
						e[j]=g=e[j]-hh*f;
						for (k=1;k<=j;k++) //Reduce a, equation (11.2.13).
							a[j][k] -= (f*e[k]+g*a[i][k]);
					}
				}
			}
			else
				e[i]=a[i][l];
			d[i]=h;
		}
		/* Next statement can be omitted if eigenvectors not wanted */
		d[1]=0.0;
		e[1]=0.0;
		/* Contents of this loop can be omitted if eigenvectors not
		wanted except for statement d[i]=a[i][i]; */
		for (i=1;i<=n;i++)
		{ //Begin accumulation of transformation matrices.
			l=i-1;
			if (d[i] != 0.0) { //This block skipped when i=1.
				for (j=1;j<=l;j++)
				{
					g=0.0;
					for (k=1;k<=l;k++) //Use u and u/H stored in a to form P·Q.
						g += a[i][k]*a[k][j];
					for (k=1;k<=l;k++)
						a[k][j] -= g*a[k][i];
				}
			}
			d[i]=a[i][i]; //This statement remains.
			a[i][i]=1.0; //Reset row and column of a to identity matrix for next iteration. 
			for (j=1;j<=l;j++) a[j][i]=a[i][j]=0.0;
		}
		
	}

}

