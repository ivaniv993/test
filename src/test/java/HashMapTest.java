import com.edu.HashMapImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


/**
 * Created by iivaniv on 08.09.2016.
 *
 */

public class HashMapTest {

    private static final Logger log = LoggerFactory.getLogger(HashMapImpl.class);

    @Test
    public void testPutGet() {
        HashMapImpl map = new HashMapImpl();

        map.put(4, 99);

        assert (map.get(4).getValue() == 99);
        assert map.size() == 1;

    }

    @Test
    public void testOverrideValue() {
        HashMapImpl map = new HashMapImpl();

        int key = 77;

        map.put(key, 99);
        map.put(key, 11);

        assert (map.get(key).getValue() == 11);
        assert map.size() == 1;

    }

    @Test
    public void testPut() {
        HashMapImpl map = new HashMapImpl();

        map.put(1, 120);

        assert map.size() == 1;
    }


    @Test
    public void testGet() {
        HashMapImpl map = new HashMapImpl();

        assert map.get(2) == null;
        assert map.size() == 0;
    }


    @Test
    public void randomTest() {

        HashMapImpl map = new HashMapImpl(20);
        Set<Integer> keys = generateRandomKey(20);
        Random rand = new Random(100);
        for (int key : keys){
            map.put(key, rand.nextInt());
        }

        for (int key : keys){
            assert map.get( key ) != null;
        }

        assert map.size() == keys.size();
    }

    private Set<Integer> generateRandomKey(int quantity) {

        Set<Integer> keys = new HashSet<Integer>();
        Random rand = new Random();
        int key = 0;
        for (int i = 0; i < quantity; i++) {
            do {
                key = rand.nextInt(quantity + 1);

            } while (!keys.add(key));
        }
        return keys;
    }


}
