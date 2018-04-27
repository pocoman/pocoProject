package WorkCarte;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JList;

public class lstList<T1, T2> extends JList<T1> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3352825489270262117L;
	ArrayList<T2> Data = null;
	
	ArrayList<T2> ConvertData(T2[] Data) {
		ArrayList<T2> result = new ArrayList<T2>();
		
		for(int i = 0; i < Data.length; i++) {
			result.add(Data[i]);
		}
		
		return result;
	}
	
	public void setListData(Vector<? extends T1> listData, T2[] Data) {
		// TODO Auto-generated method stub
		super.setListData(listData);
		this.Data = ConvertData(Data);
	}
	
	public void setListData(T1[] listData, T2[] Data) {
		// TODO Auto-generated method stub
		super.setListData(listData);
		this.Data = ConvertData(Data);
	}
	public void setListData(Vector<? extends T1> listData, ArrayList<T2> Data) {
		// TODO Auto-generated method stub
		super.setListData(listData);
		this.Data = Data;
	}
	
	public void setListData(T1[] listData, ArrayList<T2> Data) {
		// TODO Auto-generated method stub
		super.setListData(listData);
		this.Data = Data;
	}
	
	public T2 getSelectedData() {
		if(Data == null) {
			return null;
		} else {
			return Data.get(this.getSelectedIndex());
		}
	}
	
	public void setSelectedIndexByData(T2 value) {
		boolean isCheck = false;
		for(int i = 0; i < Data.size(); i++) {
			if(Data.get(i) == value) {
				this.setSelectedIndex(i);
				isCheck = true;
				break;
			}
		}
		
		if(!isCheck) this.setSelectedIndex(0);
	}
	
	public T1 getListTitleByData(T2 value) {
		if(Data == null) {
			return null;
		} else {
			for(int i = 0; i < Data.size(); i++) {
				if(Data.get(i) == value) {
					return this.getModel().getElementAt(i);
				}
			}
		}
		
		return null;
	}
	
	public void clearAllItem() {
		this.setListData(new Vector<T1>());
	}
	
	public void searchData(T1 keyword) {
		String strKey = keyword.toString();
		for(int i = 0; i < getModel().getSize(); i++) {
			String temp = getModel().getElementAt(i).toString();
			if(temp.indexOf(strKey) != -1) {
				setSelectedIndex(i);
				break;
			}
		}
	}
}
