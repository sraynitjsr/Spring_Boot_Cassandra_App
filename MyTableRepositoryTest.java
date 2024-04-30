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
