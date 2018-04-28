import org.wso2.siddhi.core.SiddhiAppRuntime;
import org.wso2.siddhi.core.SiddhiManager;
import org.wso2.siddhi.core.event.Event;
import org.wso2.siddhi.core.stream.output.StreamCallback;
import org.wso2.siddhi.core.util.EventPrinter;

public class Main {
    public static void main(String[] args) {
        String siddhiString = "@App:name(\"haha\") " +
                "@App:description(\"fasd\") " +
                "@App:statistics(reporter = \"jmx\", interval = \"30\") " +
                "@source(type=\"http\",receiver.url=\"http://localhost:9056/endpoints/\",@map(type=\"text\",fail.on.missing.attribute=\"true\",regex.A=\"(.*)\",@attributes(data=\"A\"))) " +
                "define stream a4P068X5YCK(data String);";
        SiddhiManager siddhiManager = new SiddhiManager();
        SiddhiAppRuntime siddhiAppRuntime = siddhiManager.createSiddhiAppRuntime(siddhiString);

        siddhiAppRuntime.addCallback("a4P068X5YCK", new StreamCallback() {
            @Override
            public void receive(Event[] events) {
                EventPrinter.print(events);
            }
        });

        siddhiAppRuntime.start();
    }
}
