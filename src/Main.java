public class Main
{
    public static void main(String[] args) {
        DataProcessor processor = new DataProcessor();

        // Adding data to the list
        processor.addDataToList("Data 1");
        processor.addDataToList("Data 2");

        // Adding data to the map
        processor.addDataToMap(1, "Map data 1");
        processor.addDataToMap(2, "Map data 2");

        // Adding data to the queue
        try
        {
            processor.addDataToQueue("Queue data 1");
            processor.addDataToQueue("Queue data 2");
        }
        catch (InterruptedException e)
        {
            System.err.println("Error adding data to queue: " + e.getMessage());
        }

        // Processing data
        processor.processData();
        System.out.println("Total processed count: " + processor.getProcessedCount());
    }
}
