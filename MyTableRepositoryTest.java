import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mycassandraapi.model.MyTableModel;
import com.example.mycassandraapi.repository.MyTableRepository;

@SpringBootTest
public class MyTableRepositoryTest {

    @Mock
    private MyTableRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        MyTableModel model = new MyTableModel();
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.of(model));

        Optional<MyTableModel> result = repository.findById(id);

        assertTrue(result.isPresent());
        assertEquals(model, result.get());
    }

    @Test
    public void testSave() {
        MyTableModel model = new MyTableModel();
        when(repository.save(model)).thenReturn(model);

        MyTableModel result = repository.save(model);

        assertEquals(model, result);
    }

    @Test
    public void testDeleteById() {
        UUID id = UUID.randomUUID();

        assertDoesNotThrow(() -> repository.deleteById(id));
    }

    @Test
    public void testFindByAgeBetween() {
        List<MyTableModel> data = Arrays.asList(
            new MyTableModel(UUID.randomUUID(), "Anjali", 25),
            new MyTableModel(UUID.randomUUID(), "Vijay", 28),
            new MyTableModel(UUID.randomUUID(), "Pooja", 32)
        );
        
        int minAge = 25;
        int maxAge = 30;
        
        when(repository.findByAgeBetween(minAge, maxAge)).thenReturn(
            data.subList(0, 2)
        );

        List<MyTableModel> result = repository.findByAgeBetween(minAge, maxAge);

        assertEquals(2, result.size());
        assertEquals(data.subList(0, 2), result);
    }
}
