package com.mygdx.game;

import org.usb4java.Device;

public class McuWithPeriBoard extends McuBoard
{
	private static final byte RQ_SET_LED       = 0;
    private static final byte RQ_SET_LED_VALUE = 1;
    private static final byte RQ_GET_SWITCH    = 2;
    private static final byte RQ_GET_LIGHT     = 3;
    private static final byte RQ_GET_ACCELERO_X  = 4;
    private static final byte RQ_GET_ACCELERO_Y  = 5;
    private static final byte RQ_GET_ACCELERO_Z  = 6;

    public McuWithPeriBoard(Device device) {
		super(device);
	}

    /**
     * Set status of LED on peripheral board
     * 
     * @param ledNo  the number of LED (0,1,2) to set status
     * @param value  status to set (0-off, 1-on)
     */
    public void setLed(int ledNo, int value)
    {
        this.write(RQ_SET_LED, (short) ledNo, (short) value);
    }

    /**
     * Display a binary value on the peripheral board's LEDs
     * 
     * @param value The value to be displayed on the LEDs
     */
    public void setLedValue(int value)
    {
    	this.write(RQ_SET_LED_VALUE, (short)1, (short)value);
    }

    /**
     * Check the state of the switch
     * 
     * @return true when the switch is pressed; false otherwise
     */
    public boolean getSwitch()
    {
        byte[] ret = this.read(RQ_GET_SWITCH, (short) 0, (short) 0);
        return ret[0] == 1;
    }

    /**
     * Read and return the light intensity
     * 
     * @return a value between 0-1023; the greater the intensity, the higher the value
     */
    public int getLight()
    {
    	byte[] ret = this.read(RQ_GET_LIGHT, (short)0, (short)0);
//    	return ret[0];
    	return (ret[0] & 0xFF) + (ret[1] & 0xFF)*256;
    	//return (ret[0]+256)%256 + (ret[1]*256);
        //return 0;
    }
    
    public int getAcceleroX()
    {
    	byte[] ret = this.read(RQ_GET_ACCELERO_X, (short)0, (short)0);
    	//return ret[0];
    	return (ret[0] & 0xFF) + (ret[1] & 0xFF)*256;
    }
    
    public int getAcceleroY()
    {
    	byte[] ret = this.read(RQ_GET_ACCELERO_Y, (short)0, (short)0);
    	//return ret[0];
    	return (ret[0] & 0xFF) + (ret[1] & 0xFF)*256;
    }
    public int getAcceleroZ()
    {
    	byte[] ret = this.read(RQ_GET_ACCELERO_Z, (short)0, (short)0);
    	//return ret[0];
    	return (ret[0] & 0xFF) + (ret[1] & 0xFF)*256;
    }
}
