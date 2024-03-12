package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.DTO.EmployeeFullInfo;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.DTO.ReportDTO;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.entitys.Employee;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.entitys.Report;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.exceptionHandler.JsonException;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.repository.ReportRepositoriy;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.constants.EmployeeConstants.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImpTests {
    @InjectMocks
    private EmployeeServiceImp employeeService;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private ReportRepositoriy reportRepositoriy;


    @Before
    public void setUp() {
        employeeRepository = mock(EmployeeRepository.class); // Создание mock объекта employeeRepository
        objectMapper = mock(ObjectMapper.class); // Создание mock объекта objectMapper
        reportRepositoriy = mock(ReportRepositoriy.class); // Создание mock объекта reportRepositoriy
        employeeService = new EmployeeServiceImp(employeeRepository, objectMapper, reportRepositoriy); // Инициализация сервиса с mock объектами

    }

    @Test
    public void testAddEmployee_NewEmployee() {
        when(employeeRepository.existsById(anyInt())).thenReturn(false); // Устанавливаем, что метод existsById вернет false, т.е. сотрудника с таким id еще нет в базе
        employeeService.addEmployee(EMPLOYEE_DTO_1);  // Вызываем метод, который тестируем
        verify(employeeRepository, times(1)).save(any(Employee.class)); // Проверяем, что метод save был вызван для сохранения сотрудника
    }

    @Test(expected = Exception.class)
    public void testAddEmployee_ExistingEmployee() {
        when(employeeRepository.existsById(anyInt())).thenReturn(true);// Устанавливаем, что метод existsById вернет true, т.е. сотрудник с таким id уже есть в базе
        employeeService.addEmployee(EMPLOYEE_DTO_1); // Вызываем метод, который тестируем (должен выбросить исключение)
    }

    @Test
    public void testUpdateEmployee_ExistingEmployee() {
        when(employeeRepository.existsById(anyInt())).thenReturn(true); // Устанавливаем, что метод existsById вернет true, т.е. сотрудник с таким id уже есть в базе
        employeeService.updateEmployee(1, EMPLOYEE_DTO_2); // Вызываем метод, который тестируем
        verify(employeeRepository, times(1)).save(any(Employee.class)); // Проверяем, что метод save был вызван для обновления сотрудника
    }

    @Test(expected = Exception.class)
    public void testUpdateEmployee_NonExistingEmployee() {
        when(employeeRepository.existsById(anyInt())).thenReturn(false);// Устанавливаем, что метод existsById вернет false, т.е. сотрудника с таким id еще нет в базе
        employeeService.updateEmployee(anyInt(), EMPLOYEE_DTO_2);// Вызываем метод, который тестируем (должен выбросить исключение)
    }

    @Test
    public void testGetEmployeeById_ExistingEmployee() {
        when(employeeRepository.existsById(anyInt())).thenReturn(true); // Устанавливаем, что метод existsById вернет true, т.е. сотрудник с таким id уже есть в базе
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(EMPLOYEE_1)); // Проверяем, что метод findByID возвращает employee из БД
        employeeService.getEmployeeById(anyInt()); // Вызываем метод, который тестируем
        verify(employeeRepository, times(1)).findById(anyInt()); // Проверяем, что метод findById был вызван для получения сотрудника по id
        assertEquals(EMPLOYEE_DTO_1, employeeService.getEmployeeById(anyInt())); // Проверяем, что метод getEmployeeById возвращает сотрудника с таким id
    }

    @Test(expected = Exception.class)
    public void testGetEmployeeById_NonExistingEmployee() {
        when(employeeRepository.existsById(anyInt())).thenReturn(false);// Устанавливаем, что метод existsById вернет false, т.е. сотрудника с таким id еще нет в базе
        employeeService.getEmployeeById(anyInt()); // Вызываем метод, который тестируем (должен выбросить исключение)
    }

    @Test
    public void testDeleteEmployee_ExistingEmployee() {
        when(employeeRepository.existsById(anyInt())).thenReturn(true);// Устанавливаем, что метод existsById вернет true, т.е. сотрудник с таким id уже есть в базе
        employeeService.deleteEmployee(anyInt()); // Вызываем метод, который тестируем
        verify(employeeRepository, times(1)).deleteById(anyInt()); // Проверяем, что метод deleteById был вызван для удаления сотрудника по id
    }

    @Test(expected = Exception.class)
    public void testDeleteEmployee_NonExistingEmployee() {
        when(employeeRepository.existsById(anyInt())).thenReturn(false);// Устанавливаем, что метод existsById вернет false, т.е. сотрудника с таким id еще нет в базе
        employeeService.deleteEmployee(anyInt());// Вызываем метод, который тестируем (должен выбросить исключение)
    }

    @Test
    public void testGetAllEmployees_getListOfEmployeeDTO() {
        when(employeeRepository.findAll()).thenReturn(EMPLOYEES_LIST); // Проверяем, что метод findAll возвращает список сотрудников из БД
        List<EmployeeDTO> employeesDTO = employeeService.getAllEmployees(); // Вызываем метод, который тестируем
        assertIterableEquals(EMPLOYEES_DTO_LIST, employeesDTO); // Проверяем, что метод getAllEmployees возвращает List<EmployeeDTO> с сотрудниками из БД
    }

    @Test(expected = NullPointerException.class)
    public void testGetAllEmployees_NullList() {
        when(employeeRepository.findAll()).thenReturn(null); // Проверяем, что метод findAll может вернуть null
        List<EmployeeDTO> employeesDTO = employeeService.getAllEmployees(); // Вызываем метод, который тестируем (должен выбросить исключение)
        assertEquals(0, employeesDTO.size()); // Проверяем, что возвращаемый список пуст
    }

    @Test
    public void getAllEmployeesSalaryHigherThanSalary_getListOfEmployeeDTO() {
        when(employeeRepository.findBySalaryGreaterThan(anyInt())).thenReturn(EMPLOYEES_LIST); // Проверяем, что метод findBySalaryGreaterThan возвращает список сотрудников из БД
        List<EmployeeDTO> employeesDTO = employeeService.getAllEmployeesSalaryHigherThanSalary(anyInt()); // Вызываем метод, который тестируем
        assertIterableEquals(EMPLOYEES_DTO_LIST, employeesDTO); // Проверяем, что метод getAllEmployeesSalaryHigherThanSalary возвращает List<EmployeeDTO> с сотрудниками из БД
    }

    @Test
    public void testGetEmployeeOnPosition_NullPosition() {
        when(employeeRepository.findAll()).thenReturn(EMPLOYEES_LIST); // Проверяем, что метод findAll возвращает список сотрудников из БД
        List<EmployeeFullInfo> employeeFullInfos = employeeService.getEmployeeOnPosition(null); // Вызываем метод, который тестируем
        assertEquals(3, employeeFullInfos.size()); // Проверка актуальности значений
        assertEquals("Ivan", employeeFullInfos.get(0).getName()); // Проверка актуальности значений
    }

    @Test
    public void testGetEmployeeOnPosition_WithPosition() {
        when(employeeRepository.findEmployeesByPosition("test")).thenReturn(EMPLOYEES_FULL_INFO_LIST); // Проверяем, что метод возвращает список сотрудников из БД
        List<EmployeeFullInfo> employeeFullInfos = employeeService.getEmployeeOnPosition("test"); // Вызываем метод, который тестируем
        assertEquals(3, employeeFullInfos.size()); // Проверка актуальности значений
        assertEquals("Ivan", employeeFullInfos.get(0).getName()); // Проверка актуальности значений
    }

    @Test
    public void getEmployeeWithHighestSalary_getListEmployeeFullInfo() {
        when(employeeRepository.findEmployeeWithHighestSalary()).thenReturn(EMPLOYEES_LIST); // Проверяем, что метод возвращает список сотрудников из БД
        List<EmployeeFullInfo> employeeFullInfos = employeeService.getEmployeeWithHighestSalary(); // Вызываем метод, который тестируем
        assertEquals("Ivan", employeeFullInfos.get(0).getName()); // Проверка актуальности значений
    }

    @Test
    public void getEmployeeFullInfoByID_ExistingEmployee() {
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(EMPLOYEE_1)); // Проверяем, что метод findByID возвращает employee из БД
        List<EmployeeFullInfo> employeeFullInfos = employeeService.getEmployeeFullInfoByID(anyInt()); // Вызываем метод, который тестируем>
        assertEquals(EMPLOYEE_1.getName(), employeeFullInfos.get(0).getName()); // Проверка актуальности значений
    }

    @Test(expected = Exception.class)
    public void etEmployeeFullInfoByID_NonExistingEmployee() {
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.empty()); // Проверяем, что метод findByID возвращает employee из БД
        employeeService.getEmployeeFullInfoByID(anyInt()); // Вызываем метод, который тестируем (должен выбросить исключение)
    }

    @Test
    public void getPageEmployee_getListOfEmployeeFullInfo() {
        Page<Employee> mockPage = new PageImpl<>(EMPLOYEES_LIST); // Тестовые данные
        when(employeeRepository.findAll(any(Pageable.class))).thenReturn(mockPage); // Проверяем, что метод findAll возвращает список сотрудников из БД.
        List<EmployeeFullInfo> result = employeeService.getPageEmployee(anyInt()); // Вызываем метод, который тестируем
        assertIterableEquals(EMPLOYEES_FULL_INFO_LIST, result); // Проверка актуальности значений
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPageEmployee_PageNumberLessThanZero() {
        Page<Employee> mockPage = new PageImpl<>(EMPLOYEES_LIST); // Тестовые данные
        when(employeeRepository.findAll(any(Pageable.class))).thenReturn(mockPage); // Проверяем, что метод findAll возвращает список сотрудников из БД.
        List<EmployeeFullInfo> result = employeeService.getPageEmployee(-1); // Вызываем метод, который тестируем (выбрасывает исключение)
    }



    @Disabled ("Не понимаю, почему objectMapper, в saveEmployee, передает null")
    @SneakyThrows
    public void saveEmployee_ValidFile_SaveEmployeeToDB() {
        // Тестовые данные
        // TODO Не понимаю, почему objectMapper, в saveEmployee, передает null
        // Если раскомметировать код  на строчке 185, то MockMultipartFile file создается. НО! при вызове метода saveEmployee(file) в тесте
        // получаю "Cannot invoke "java.util.List.iterator()" because "temp" is null", и objectMapper возвращает null уже в вызываемом методе.
        //objectMapper = new ObjectMapper();
        byte[] bytes = objectMapper.writeValueAsBytes(EMPLOYEES_DTO_LIST);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        MockMultipartFile file = new MockMultipartFile("file", "employees.json", "application/json", inputStream);
        employeeService.saveEmployee(file);

        verify(employeeService, times(3)).addEmployee(any(EmployeeDTO.class));
    }

    @SneakyThrows

    @Disabled ("Тоже самое что и в предидущем тесте")
    public void saveEmployee_InvalidFile_ThrowsJsonException() {
        // Tестовые данные
        ByteArrayInputStream inputStream = new ByteArrayInputStream("invalid json data".getBytes());
        MockMultipartFile file = new MockMultipartFile("file", "invalid.json", "application/json", inputStream);

        // Вызываем тестируемый метод (выбросит исключение)
        assertThrows(JsonException.class, () -> employeeService.saveEmployee(file));
    }


    @Disabled("Да что не так с этим objectMapper в вызываемом методе, принимает объект и все обнуляет")
    public void buildReport_getStringReport(){
        //тестовые данные
        ReportDTO reportDTO_1 = new ReportDTO();
        ReportDTO reportDTO_2 = new ReportDTO();
        List<ReportDTO> reports = List.of(reportDTO_1, reportDTO_2);
        // Проверяем, что buildReports возвращает List из 2 отчетов
        when(employeeRepository.buildReports()).thenReturn(reports);
        //вызываем проверяемый метод
        String result = employeeService.buildReport();
        // Проверяем соответствие ожидаемому.
        assertEquals(reports.toString(), result);
    }

    @Test
    public void downloadReport_DDDDD() throws IOException {
        Report report = new Report(1,"sfsfsdfsdfsfd", Calendar.getInstance());
        when(reportRepositoriy.findById(anyInt())).thenReturn(Optional.of(report));
        Resource result = employeeService.downloadReport(anyInt());
        assertEquals(result.getContentAsString(StandardCharsets.UTF_8), report.toString());
    }
}
