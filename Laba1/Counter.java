public class Counter {
    public int count = 0;

    public void incrementNotSynchron(){
        count++;
    }
    public void decrementNotSynchron(){
        count--;
    }

    public synchronized void incrementSynchronMethod(){
        count++;
    }
    public synchronized void decrementSynchronMethod(){
        count--;
    }

    public void incrementSynchronBlock(){
        synchronized (this) {
            count++;
        }
    }

    public void decrementSynchronBlock(){
        synchronized (this) {
            count--;
        }
    }

    public int getCount(){
        return count;
    }
}
