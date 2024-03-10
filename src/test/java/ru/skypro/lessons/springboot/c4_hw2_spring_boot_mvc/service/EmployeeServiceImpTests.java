package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.entitys.Employee;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.repository.ReportRepositoriy;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
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

    @Test
    public void testUpdateEmployee_ExistingEmployee() {
        when(employeeRepository.existsById(anyInt())).thenReturn(true); // Устанавливаем, что метод existsById вернет true, т.е. сотрудник с таким id уже есть в базе
        employeeService.updateEmployee(1, EMPLOYEE_DTO_2); // Вызываем метод, который тестируем
        verify(employeeRepository, times(1)).save(any(Employee.class)); // Проверяем, что метод save был вызван для обновления сотрудника
    }

    @Test
    public void testGetEmployeeById_ExistingEmployee() {
        when(employeeRepository.existsById(anyInt())).thenReturn(true); // Устанавливаем, что метод existsById вернет true, т.е. сотрудник с таким id уже есть в базе
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(EMPLOYEE_1)); // Проверяем, что метод findByID возвращает employee из БД
        employeeService.getEmployeeById(1); // Вызываем метод, который тестируем
        verify(employeeRepository, times(1)).findById(anyInt()); // Проверяем, что метод findById был вызван для получения сотрудника по id
        assertEquals(EMPLOYEE_DTO_1, employeeService.getEmployeeById(anyInt())); // Проверяем, что метод getEmployeeById возвращает сотрудника с таким id

    }

    @Test
    public void testDeleteEmployee_ExistingEmployee() {
        when(employeeRepository.existsById(anyInt())).thenReturn(true);// Устанавливаем, что метод existsById вернет true, т.е. сотрудник с таким id уже есть в базе
        employeeService.deleteEmployee(anyInt()); // Вызываем метод, который тестируем
        verify(employeeRepository, times(1)).deleteById(anyInt()); // Проверяем, что метод deleteById был вызван для удаления сотрудника по id
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


    @Test(expected = Exception.class)
    public void testAddEmployee_NonExistingEmployee() {
        when(employeeRepository.existsById(anyInt())).thenReturn(true);// Устанавливаем, что метод existsById вернет true, т.е. сотрудник с таким id уже есть в базе
        employeeService.addEmployee(EMPLOYEE_DTO_1); // Вызываем метод, который тестируем (должен выбросить исключение)
    }

    @Test(expected = Exception.class)
    public void testUpdateGetDeleteEmployee_NonExistingEmployee() {
        when(employeeRepository.existsById(anyInt())).thenReturn(false);// Устанавливаем, что метод existsById вернет false, т.е. сотрудника с таким id еще нет в базе
        employeeService.updateEmployee(1, EMPLOYEE_DTO_2);// Вызываем метод, который тестируем (должен выбросить исключение)
        employeeService.getEmployeeById(1); // Вызываем метод, который тестируем (должен выбросить исключение)
        employeeService.deleteEmployee(1);// Вызываем метод, который тестируем (должен выбросить исключение)
    }


}
