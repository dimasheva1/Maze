import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class TimerLabel extends JLabel
{
private volatile int time;
private boolean flag = false;

	public TimerLabel (Timer timer,int time)
	
	{
		this.time=time;
		timer.scheduleAtFixedRate(timerTask, 0, 10); //отвечает за то что каждые 10 миллисекунд что-то делает
	}
	
	public int getTime()
	{
		return time;
	}
	
	public void start()
	{
		flag=true;
	}
	
	public void stop()
	{
		flag=false;
	}
	public void setTime(int time)
	{
		this.time=time;
	}
	
	
	private TimerTask timerTask = new TimerTask()
	{

		private Runnable refresher = new Runnable()
		{
			@Override
			public void run ()
			{
				int t = time;
				TimerLabel.this.setText("Осталось времени - " + String.format("%02d:%02d", t / 100, t % 100));
			}
		};
		
		@Override
		public void run ()	// То что делает TimerTask
		{
			if (flag)
			{
			time--;
			SwingUtilities.invokeLater(refresher); // метод помещает в очередь событий, когда очередь выполн run. Исп для отложеных действий или 
			}										   //когда нужно выполнить что-то в UI thread
		}
	
	};
	
	
}
