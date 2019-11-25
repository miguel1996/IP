import javafx.util.Pair;
import java.lang.Thread;
import processing.serial.*;

Pair<Float, Float> position = new Pair(0.0, 0.0); 
Serial myPort;  

void setup(){
  print(position + "\n");
  try{
    print(Serial.list()[0]);
    myPort = new Serial(this, Serial.list()[0], 115200);
  } 
  catch(Exception e){
    print("No serial connection detected!\n");
  }
}

void draw(){
  moveBackward();
  moveForward();
  moveLeft();
  moveRight();
  reset();
}

void moveForward(){
  while(position.getValue() < 1.0){
    position = new Pair(position.getKey(), position.getValue() + 0.1);
    print(position + "\n");
    sendData();
    sleep();
  }
}

void moveBackward(){
  while(position.getValue() > -1.0){
    position = new Pair(position.getKey(), position.getValue() - 0.1);
    print(position + "\n");
    sendData();
    sleep();
  }
}

void moveRight(){
  while(position.getKey() < 1.0){
    position = new Pair(position.getKey()+ 0.1, position.getValue());
    print(position + "\n");
    sendData();
    sleep();
  }
}

void moveLeft(){
  while(position.getKey() > -1.0){
    position = new Pair(position.getKey()- 0.1, position.getValue());
    print(position + "\n");
    sendData();
    sleep();
  }
}

void reset(){
  position = new Pair(0.0, 0.0);
}

void sleep(){
  try{
      Thread.sleep(200);
    }
    catch(Exception e){
      print("Something has gone wrong");
    }
}

void sendData(){
  try{
    myPort.write(position.getKey() + " " + position.getValue());
    print("Success\n");
  }
  catch(Exception e){
    print("Unable to send data");
  }
}
