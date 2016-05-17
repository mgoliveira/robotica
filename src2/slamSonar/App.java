package slamSonar;

import ev3dev.hardware.motor.EV3LargeRegulatedMotor;
import ev3dev.hardware.port.MotorPort;
import ev3dev.hardware.port.SensorPort;
import ev3dev.hardware.sensor.ev3.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class App 
{

	private final static EV3LargeRegulatedMotor mA = new EV3LargeRegulatedMotor(MotorPort.A);
	private final static EV3LargeRegulatedMotor mB = new EV3LargeRegulatedMotor(MotorPort.B);
	private final static EV3UltrasonicSensor sensorA = new EV3UltrasonicSensor(SensorPort.S1);
	private final static int motorSpeed = 500;


	//Robot control loop


	public static void main( String[] args )
	{

		final SampleProvider sp = sensorA.getDistanceMode();
		int distance = 255;
		final int distance_threshold = 60;
		float [] sample = new float[sp.sampleSize()];


		System.out.println( "Iniciou!" );

		mA.setSpeed(motorSpeed);
		mB.setSpeed(motorSpeed);

		
		while(true) {
			mA.forward();
			mB.forward();

			sp.fetchSample(sample, 0);
			distance = (int) sample[0];
			System.out.println(distance);
			if(distance > distance_threshold){
				break;
			}
		}
		
		System.out.println( "Finalizou!" );
		mA.stop();
		mB.stop();
	}
}
