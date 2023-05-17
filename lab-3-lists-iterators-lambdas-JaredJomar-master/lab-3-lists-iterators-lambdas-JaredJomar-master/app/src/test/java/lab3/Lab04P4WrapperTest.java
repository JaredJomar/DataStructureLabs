package lab3;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Lab04P4WrapperTest {
	
    private Lab04P4Wrapper.List<Object> list;
    
    @Before
    public void setUp() {
        list = new Lab04P4Wrapper.ArrayList<>();
    }

    @Test
    public void checkEmpty(){
        assertTrue("Fails to return true on empty list", Lab04P4Wrapper.checkReflection(list));
    }

    @Test
    public void checkOneElm(){
        list.add(1);
        assertTrue("Fails to return true with one element list", Lab04P4Wrapper.checkReflection(list));
    }


    @Test
    public void checkEven(){
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(1);
        assertTrue("Fails to return true on list", Lab04P4Wrapper.checkReflection(list));
        
        list.clear();
        
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1);
        assertFalse("Fails to return false on list", Lab04P4Wrapper.checkReflection(list));
    }

    @Test
    public void checkOdd(){
        list.add(1);
        list.add(2);
        list.add(9);
        list.add(3);
        list.add(1);
        assertFalse("Fails to return false on list", Lab04P4Wrapper.checkReflection(list));
        
        list.clear();
        
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(2);
        list.add(1);
        assertTrue("Fails to return true on list", Lab04P4Wrapper.checkReflection(list));
    }
}