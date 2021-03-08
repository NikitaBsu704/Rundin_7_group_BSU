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
	// ������������ ������ �������� �����
	private ArrayList<BouncingBall> balls = new ArrayList<BouncingBall>(10);
	// ����� ������ �������� �� ���������� ��������� ������� ActionEvent
	// ��� �������� ��� ���������� ������������ ��������� �����, 
	// ����������� ��������� ActionListener
	int time = 0;
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
		
		BouncingBall CurrentBall = balls.get(1);
		
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
					paused = true;CurrentBall = ball;break;
				}
			}
	    }
	    
	    public void mouseReleased(MouseEvent Event3) 
	    {
	    	///�� ������ ���� ������������ �������� ����� ������� ���� 
	    	///(10 ����� - ����� 100�� ����� ������� ��������)
	    	if(time >= 10)
	    	{
		    	System.out.print ("MouseReleased3");
		    	double X_end,Y_end,R;
		    	X_end = Event3.getX();
		    	Y_end = Event3.getY();
		    	R = Math.sqrt((X_end-Mouse_x)*(X_end-Mouse_x)+(Y_end-Mouse_y)*(Y_end-Mouse_y));
		    	CurrentBall.SetDirection((X_end-Mouse_x)/R,(Y_end-Mouse_y)/R);
		    	CurrentBall.SetSpeed(//integer needed:(R/time));
	    	}
	    	///��� ����������� �� ������� �������� �����������
	    	paused = false;notifyAll();
	    }
	  }
	  
	  public class MouseMotionHandler implements MouseMotionListener 
	  {
		  
	    public void mouseMoved(MouseEvent Event4) 
	    {
	         ///just nothing....
	    }
	    
	    public void mouseDragged(MouseEvent Event5) 
	    {
	    	time = 0;
	    	while(time < 1000)
	    	{
	    		///��������� �� ������������� �����
	    		///��� �� ����� ��� �������� ���������� �����
	    		Thread.sleep(10);
	    		time = time+1;
	    	}
	    	
	    	
	    	
	    }
	  }
	// ����������� ������ BouncingBall
	public Field() 
	{
		// ���������� ���� ������� ���� �����
		setBackground(Color.WHITE);
		addMouseListener(new MouseHandler());
	    addMouseMotionListener(new MouseMotionHandler());
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
