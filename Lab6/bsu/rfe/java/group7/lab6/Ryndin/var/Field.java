package bsu.rfe.java.group7.lab6.Ryndin.var;

import java.awt.LayoutManager;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
@SuppressWarnings("serial")
public class Field extends JPanel
{
	// ���� ������������������ ��������
	private boolean paused;
	private boolean MouseClick;
	// ������������ ������ �������� �����
	private ArrayList<BouncingBall> balls = new ArrayList<BouncingBall>(10);
	// ����� ������ �������� �� ���������� ��������� ������� ActionEvent
	// ��� �������� ��� ���������� ������������ ��������� �����, 
	// ����������� ��������� ActionListener
	private Timer repaintTimer = new Timer(10, new ActionListener() 
	{
		public void actionPerformed(ActionEvent ev) 
		{
		// ������ ����������� ������� ActionEvent - ����������� ����
		repaint();
		}
	});
	
	public class MouseHandler extends MouseAdapter 
	{
		
		int Mouse_x = 0,Mouse_y = 0,i = 0;
		long TimeClicked = 0,TimeReleased = 0;
		
		
		public void mouseClicked(MouseEvent Event1) 
		{
			
			 ///just nothing....	
		}
	    
	    public void mousePressed(MouseEvent Event2) 
	    {
	    	System.out.print ("MousePressed2");
			Mouse_x = Event2.getX();
			Mouse_y = Event2.getY();
			for (BouncingBall ball: balls)
			{
				if(ball.CheckDistance(Mouse_x, Mouse_y)) 
				{
					pause();break;
				}
			}
			TimeClicked = System.currentTimeMillis();
			
	    }
	    
	    public void mouseReleased(MouseEvent Event3) 
	    {
	    	///�� ������ ���� ������������ �������� ����� ������� ���� 
	    	///(10 ����� - ����� 100�� ����� ������� ��������)
	    	TimeReleased = System.currentTimeMillis();
	    	double time = 0;
	    	time = TimeReleased - TimeClicked;
	    	if(time >= 100 )
	    	{
	    		///������������� ����� ����������
		    	System.out.print ("MouseReleased3");
		    	int X_end,Y_end,speed1;
		    	double R,Cos,Sin;
		    	X_end = Event3.getX();
		    	Y_end = Event3.getY();
		    	R = Math.sqrt((X_end-Mouse_x)*(X_end-Mouse_x)+(Y_end-Mouse_y)*(Y_end-Mouse_y));
		    	if(R < 0.1) {R = 0.1;}
		    	Cos = (X_end-Mouse_x)/R;
		    	Sin = (Y_end-Mouse_y)/R;
		    	R = R*10;
		    	speed1 = (int)(R/time);
		    	///��������� ������ �����
		    	///���� ������ � ������ ����-������������� ����� ���������
		    	for (BouncingBall ball: balls)
				{
					if(ball.CheckDistance(Mouse_x, Mouse_y)) 
					{
						ball.SetDirection(3*Cos,3*Sin);
						ball.SetSpeed(speed1);
					}
				}
		    	
	    	}
	    	///��� ����������� �� ������� �������� �����������
	    	resume();
	    }
	  }
	  
	  /*
	  public class MouseMotionHandler implements MouseMotionListener 
	  {
		  
	    public void mouseMoved(MouseEvent Event4) 
	    {
	         ///just nothing....
	    }
	    
	    public void mouseDragged(MouseEvent Event5) 
	    {
	    	///just nothing....
	    		
	    }
	  }
	  */
	// ����������� ������ BouncingBall
	public Field() 
	{
		// ���������� ���� ������� ���� �����
		setBackground(Color.WHITE);
	    addMouseListener(new MouseHandler());
	    // addMouseMotionListener(new MouseMotionHandler());
		// ��������� ������
		repaintTimer.start();
	}
	// �������������� �� JPanel ����� ����������� ����������
	public void paintComponent(Graphics g) 
	{
		// ������� ������ ������, �������������� �� ������
		super.paintComponent(g);
		Graphics2D canvas = (Graphics2D) g;
		// ��������������� ��������� ���������� �� ���� ����� �� ������
		for (BouncingBall ball: balls)
		{
		ball.paint(canvas);
		}
	}
	// ����� ���������� ������ ���� � ������
	public void addBall() 
	{
		//����������� � ���������� � ������ ������ ���������� BouncingBall
		// ��� ������������� ���������, ��������, �������, ����� 
		// BouncingBall ��������� ��� � ������������
		balls.add(new BouncingBall(this));
	}
	// ����� ������������������, �.�. ������ ���� ����� ����� 
	// ������������ ���� ������
	public synchronized void pause() 
	{
		// �������� ����� �����
		paused = true;
	}
	// ����� ������������������, �.�. ������ ���� ����� ����� 
	// ������������ ���� ������
	public synchronized void resume() 
	{
		// ��������� ����� �����
		paused = false;
		// ����� ��� ��������� ����������� ������
		notifyAll();
	}
	/*
	public void ActivateClick(int Mouse_x,int Mouse_y,double cos,double sin,int speed)
	{
		double R;
		for (BouncingBall ball: balls)
		{
			if(ball.CheckDistance(Mouse_x, Mouse_y)) 
			{
				ball.SetDirection(3*cos,3*sin);
				ball.SetSpeed(speed);
			}
		}
	}
	*/
	
	// ������������������ ����� ��������, ����� �� ��� ��������� 
	// (�� ������� �� ����� �����?)
	public synchronized void canMove(BouncingBall ball) throws
	InterruptedException 
	{
		if (paused) 
		{
			// ���� ����� ����� �������, �� �����, �������� 
			// ������ ������� ������, ��������
			wait();
		}
	}
}
