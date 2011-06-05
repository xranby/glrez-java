package org.mars.toolkit.demo;

public class Timer
{
  boolean started;
  long pauseTime;
  long baseTime;
  long startTime;
  
  
  public final synchronized void start()
  {
    if(!isStarted())
    {
      startTime = baseTime = now();
      started = true;
    }
    else if(isPaused())
    {
      pause();
    }
  }
  
  
  public final synchronized boolean isStarted()
  {
    return started;
  }
  
  
  public final synchronized void pause()
  {
    if(started)
    {
      if(isPaused())
      { //already paused
        baseTime = now() - (pauseTime - baseTime);
        pauseTime = 0L;
      }
      else {
        pauseTime = now();
      }
      
    }
  }
  
  
  public final synchronized boolean isPaused()
  {
    return (pauseTime > 0);
  }
  
  
  public final synchronized void stop()
  {
    started = false;
  }

  
  public final synchronized long getStartTime()
  {
    if(started) {
      return startTime;
    }
    else {
      return 0L;
    }
  }
  
  
  public final synchronized long getBaseTime()
  {
    if(started) {
      return baseTime;
    }
    else {
      return 0L;
    }
  }
  
  
  public final synchronized long getElapsed()
  {
    if(started)
    {
      if(isPaused()) {
        return pauseTime - baseTime;
      }
      else {
        return now() - baseTime;
      }
    }
    else {
      return 0L;
    }
  }
  
  
  protected long now() //can be overriden to get time from another source
  {
    return System.currentTimeMillis();
  }
  
}
