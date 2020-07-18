import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Car{
    private int power;
    private String model;

    private double getPowerInKW(){
        return power * 1.33;
    }

    @Override
    public String toString() {
        return "Car{" +
                "power=" + power +
                ", model='" + model + '\'' +
                '}';
    }
}
public class ReflectionDemo {
    public static void main(String[] args) {
        Class<Car> clazz = Car.class;
        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m: methods){
            System.out.println(m.getName());
        }
        for(Field f: fields){
            System.out.println(f.getName());
            System.out.println(f.getType().getName());
        }
        try {
            Object carObj = clazz.getDeclaredConstructor().newInstance();
            Car car = (Car) carObj;
            System.out.println(car);
            Field powerField = clazz.getDeclaredField("power");
            powerField.setAccessible(true);
            powerField.set(car, 100);
            System.out.println(car);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
