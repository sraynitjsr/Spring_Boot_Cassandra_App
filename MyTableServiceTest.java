import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mycassandraapi.model.MyTableModel;
import com.example.mycassandraapi.repository.MyTableRepository;
import com.example.mycassandraapi.service.MyTableService;

@SpringBootTest
public class MyTableServiceTest {

    @Mock
    private MyTableRepository repository;

    @InjectMocks
    private MyTableService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllData() {
        List<MyTableModel> data = Arrays.asList(
            new MyTableModel(UUID.randomUUID(), "Ramesh", 35),
            new MyTableModel(UUID.randomUUID(), "Suresh", 28)
        );
        when(repository.findAll()).thenReturn(data);

        List<MyTableModel> result = service.getAllData();

        assertEquals(data, result);
    }

    @Test
    public void testGetDataById() {
        MyTableModel model = new MyTableModel(UUID.randomUUID(), "Priya", 22);
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.of(model));

        MyTableModel result = service.getDataById(id);

        assertEquals(model, result);
    }

    @Test
    public void testSaveData() {
        MyTableModel model = new MyTableModel(UUID.randomUUID(), "Arun", 30);
        when(repository.save(model)).thenReturn(model);

        MyTableModel result = service.saveData(model);

        assertEquals(model, result);
    }

    @Test
    public void testDeleteData() {
        UUID id = UUID.randomUUID();

        assertDoesNotThrow(() -> service.deleteData(id));
    }

    @Test
    public void testGetNamesInRange() {
        List<MyTableModel> data = Arrays.asList(
            new MyTableModel(UUID.randomUUID(), "Anjali", 25),
            new MyTableModel(UUID.randomUUID(), "Vijay", 28),
            new MyTableModel(UUID.randomUUID(), "Pooja", 32)
        );

        int minAge = 25;
        int maxAge = 30;

        when(repository.findByAgeBetween(minAge, maxAge)).thenReturn(
            data.stream()
                .filter(m -> m.getAge() >= minAge && m.getAge() <= maxAge)
                .collect(Collectors.toList())
        );

        List<String> expectedNames = Arrays.asList("Anjali", "Vijay");
        List<String> result = service.getNamesInRange(minAge, maxAge);

        assertEquals(expectedNames, result);
    }
}
