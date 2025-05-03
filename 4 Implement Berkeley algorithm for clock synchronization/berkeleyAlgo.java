import java.util.*;


class Node{
    public String name;
    public double time;

    Node(String name,double time){
        this.name=name;
        this.time=time;
    }

    public double getTimeWithDelay(){
        double delay=0.4+Math.random()*0.4;
        return time+delay;
    }
}


class berkeleyAlgo {

    public static void main(String[] args) {
        ArrayList<Node> nodes=new ArrayList<>();
        Node master=new Node("master", 100);
        Node slave1=new Node("s1",99.5);
        Node slave2=new Node("s2",100.4);
        Node slave3=new Node("s3",98);

        nodes.add(master);
        nodes.add(slave1);
        nodes.add(slave2);
        nodes.add(slave3);

        HashMap<String,Double> deltas=new HashMap<>();

        for(Node it: nodes){
            if(it.name.equals("master")==false){
                double receivedTime=it.getTimeWithDelay();
                double delta=receivedTime-it.time;
                deltas.put(it.name,delta);
                // System.out.println(it.name+" "+"receivedTime: "+receivedTime+" delta: "+delta);
                System.out.printf("%s receivedTime: %.2f -> delta: %.2f\n",it.name,receivedTime,delta);
            }
        }


        deltas.put("master",0.0);//for master

        double sum=0;

        for(Map.Entry<String,Double> it: deltas.entrySet()){
            sum+= it.getValue();
        }
        
        double averageTime=sum/deltas.size();

        System.out.printf("averageTime: %.2f \n",averageTime);

        System.out.println("Corrections-");

        for(Node it: nodes){
            if(it.name.equals("master")==false){
                double adjustment= deltas.getOrDefault(it.name, 0.0)-averageTime;
                it.time+=adjustment;
                System.out.printf("%s adjustment: %.2f -> corrected: %.2f \n",it.name,adjustment,it.time);
            }
        }


    }


};