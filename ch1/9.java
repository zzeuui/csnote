import java.util.ArrayList;
import java.util.List;

interface Subject {
    public void register(Observer obj);
    public void notifyObservers();
    public Object getUpdate(Observer obj);
}

interface Observer {
    public void update(); 
}

//Topic:주체이자 객체
class Topic implements Subject {
    private List<Observer> observers;
    private String message; 

    public Topic() {
        this.observers = new ArrayList<>();
        this.message = "";
    }

    @Override
    public void register(Observer obj) {
        if (!observers.contains(obj)) observers.add(obj); 
    }

    @Override
    public void notifyObservers() {   
        this.observers.forEach(Observer::update); // 각 옵저버에게 업데이트된 메시지를 리턴하는 함수를 호출 (line 36으로)
    }

    @Override
    public Object getUpdate(Observer obj) {
        return this.message; //메시지를 리턴함(line58로 이동)
    } 


    public void postMessage(String msg) { //argument로 msg를 받으면
        System.out.println("Message sended to Topic: " + msg);
        this.message = msg; // Topic의 message가 msg로 설정되고
        notifyObservers(); //Topic의 상태 변화를 옵저버에게 알림(line31으로)
    }
}

//TopicSubscriber: 옵저버
class TopicSubscriber implements Observer {
    private String name;
    private Subject topic;

    public TopicSubscriber(String name, Subject topic) {
        this.name = name;
        this.topic = topic;
    }

    @Override
    public void update() {
        String msg = (String) topic.getUpdate(this); 
        System.out.println(name + ":: got message >> " + msg); 
    } 
}

public class HelloWorld { 
    public static void main(String[] args) {
        Topic topic = new Topic(); 
        Observer a = new TopicSubscriber("a", topic);
        Observer b = new TopicSubscriber("b", topic);
        topic.register(a);
        topic.register(b);
   
        topic.postMessage("amumu is op champion!!"); 
    }
}
/*
Message sended to Topic: amumu is op champion!!
a:: got message >> amumu is op champion!!
b:: got message >> amumu is op champion!!
*/ 
