import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CarTest {
    public Car myCar = Mockito.mock(Car.class);

    @Test
    public void test1(){
        Assertions.assertTrue(myCar instanceof  Car);
    }

    @Test
    public void test2(){
        Assertions.assertFalse(myCar.needsFuel(), "my car needs fuel");
    }

    @Test
    public void test3(){;
        Mockito.when(myCar.needsFuel()).thenReturn(true);
        Mockito.when(myCar.needsFuel()).thenThrow(new RuntimeException());
    }

    @Test
    public void test4(){;
        myCar.needsFuel();
        Mockito.verify(myCar).needsFuel();
    }
}
