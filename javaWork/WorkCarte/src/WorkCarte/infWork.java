package WorkCarte;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

public interface infWork {
	GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice GD = GE.getDefaultScreenDevice();
	int CONSOLE_WIDTH = GD.getDisplayMode().getWidth(); 
	int CONSOLE_HEIGHT = GD.getDisplayMode().getHeight();
	
	int[] FRAME_WIDTH = {1000, 400, 600};
	int[] FRAME_HEIGHT = {600, 600, 150};
	int FRM_MAIN = 0;
	int FRM_PROJECT = 1;
	int FRM_PRONEW = 2;
	
	Dimension[] FRAME_SIZE = {
			new Dimension(FRAME_WIDTH[FRM_MAIN], FRAME_HEIGHT[FRM_PROJECT])
			, new Dimension(FRAME_WIDTH[FRM_PROJECT], FRAME_HEIGHT[FRM_PROJECT])
			, new Dimension(FRAME_WIDTH[FRM_PRONEW], FRAME_HEIGHT[FRM_PRONEW])
	};
	Point[] FRAME_LOCATION = {
			new Point(CONSOLE_WIDTH / 2 - FRAME_WIDTH[FRM_MAIN] / 2, CONSOLE_HEIGHT / 2 - FRAME_HEIGHT[FRM_PROJECT] / 2)
			, new Point(CONSOLE_WIDTH / 2 - FRAME_WIDTH[FRM_PROJECT] / 2, CONSOLE_HEIGHT / 2 - FRAME_HEIGHT[FRM_PROJECT] / 2)
			, new Point(CONSOLE_WIDTH / 2 - FRAME_WIDTH[FRM_PRONEW] / 2, CONSOLE_HEIGHT / 2 - FRAME_HEIGHT[FRM_PRONEW] / 2)
	};
	
	int[] PANEL_WIDTH = {300, 150};
	int[] PANEL_HEIGHT = {FRAME_HEIGHT[FRM_MAIN], 0};
	int PNL_WORKLIST = 0;
	int PNL_PROEDIT = 1;

	Dimension[] PANEL_SIZE = {
			new Dimension(PANEL_WIDTH[PNL_WORKLIST], PANEL_HEIGHT[PNL_WORKLIST])
			, new Dimension(PANEL_WIDTH[PNL_PROEDIT], PANEL_HEIGHT[PNL_PROEDIT])
	};
	
	String REDMINE_BASIC_URL = "https://redmine.njc-web.info/";
	String REDMINE_PROJECT = "projects";
	String REDMINE_TICKET = "issues";
	String REDMINE_NEW_TICKET = "issues/new";
	
	String TABLE_PROJECT = "project_data";
	String TABLE_TICKET = "carte_data";

	int DATA_PR_ID = 0;
	int DATA_TICKET = 1;
	int DATA_TITLE = 2;
	int DATA_START = 3;
	int DATA_END = 4;
	int DATA_HOUR = 5;
	int DATA_MEMO = 6;
	int DATA_CARTE = 7;
	
	
	 
}
