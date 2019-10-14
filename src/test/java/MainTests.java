import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MainTests {

    @Test
    public void getAverage_ShouldReturnAverage() {
        List<Double> values = new ArrayList<>() {{
            add(4.0);
            add(6.0);
            add(5.0);
        }};

        Assert.assertEquals(5.0, Main.getAverage(values), 0.1);
    }

}
