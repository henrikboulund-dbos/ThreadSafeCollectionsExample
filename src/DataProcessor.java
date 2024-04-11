import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class DataProcessor
{
    private CopyOnWriteArrayList<String> dataList;
    private ConcurrentHashMap<Integer, String> dataMap;
    private BlockingQueue<String> dataQueue;
    private int counter;

    public DataProcessor()
    {
        dataList = new CopyOnWriteArrayList<>();
        dataMap = new ConcurrentHashMap<>();
        dataQueue = new LinkedBlockingQueue<>();
        counter = 0;
    }

    public void addDataToList(String data)
    {
        dataList.add(data);
    }

    public void addDataToMap(int key, String value)
    {
        dataMap.put(key, value);
    }

    public void addDataToQueue(String data) throws InterruptedException
    {
        dataQueue.put(data);
    }

    public synchronized void processData()
    {
        for (String data : dataList)
        {
            System.out.println("Processing data from list: " + data);
        }
        for (Integer key : dataMap.keySet())
        {
            System.out.println("Processing data from map: " + dataMap.get(key));
        }
        while (!dataQueue.isEmpty())
        {
            try
            {
                String data = dataQueue.take();
                System.out.println("Processing data from queue: " + data);
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                System.err.println("Error processing data from queue: " + e.getMessage());
            }
        }
        counter += dataList.size() + dataMap.size() + dataQueue.size();
    }

    public synchronized int getProcessedCount()
    {
        return counter;
    }
}
