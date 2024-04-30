import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mycassandraapi.controller.MyTableController;
import com.example.mycassandraapi.model.MyTableModel;
import com.example.mycassandraapi.service.MyTableService;

@SpringBootTest
public class MyTableControllerTest {

    @Mock
    private MyTableService service;

    @InjectMocks
    private MyTableController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllData() {
        List<MyTableModel> data = Arrays.asList(
            new MyTableModel(UUID.randomUUID(), "Rajesh", 35),
            new MyTableModel(UUID.randomUUID(), "Deepika", 28)
        );
        when(service.getAllData()).thenReturn(data);

        List<MyTableModel> result = controller.getAllData();

        assertEquals(data, result);
    }

    @Test
    public void testGetDataById() {
        MyTableModel model = new MyTableModel(UUID.randomUUID(), "Priya", 22);
        UUID id = UUID.randomUUID();
        when(service.getDataById(id)).thenReturn(model);

        MyTableModel result = controller.getDataById(id);

        assertEquals(model, result);
    }

    @Test
    public void testSaveData() {
        MyTableModel model = new MyTableModel(UUID.randomUUID(), "Arun", 30);
        when(service.saveData(model)).thenReturn(model);

        MyTableModel result = controller.saveData(model);

        assertEquals(model, result);
    }

    @Test
    public void testDeleteData() {
        UUID id = UUID.randomUUID();

        assertDoesNotThrow(() -> controller.deleteData(id));
    }

    @Test
    public void testGetNamesInRange() {
        List<String> names = Arrays.asList("Rajesh", "Deepika");
        int minAge = 25;
        int maxAge = 30;
        
        when(service.getNamesInRange(minAge, maxAge)).thenReturn(names);

        List<String> result = controller.getNamesInRange(minAge, maxAge);

        assertEquals(names, result);
    }
}
