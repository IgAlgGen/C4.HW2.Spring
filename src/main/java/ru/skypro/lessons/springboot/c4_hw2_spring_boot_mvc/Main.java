package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.DTO.EmployeeDTO;

public class Main {
  public static void main(String[] args) {

//Сериализуем объект EmployeeDTO
// в JSON с помощью метода writeValueAsString

      EmployeeDTO employeeDTO = new EmployeeDTO();
      employeeDTO.setName("Василий");
      employeeDTO.setSalary(125000);

      ObjectMapper objectMapper = new ObjectMapper();
      String json = null;
      try {
          json = objectMapper.writeValueAsString(employeeDTO);
      } catch (JsonProcessingException e) {
          throw new RuntimeException(e);
      }
      System.out.println(json);

//получить объект из строки JSON с помощью метода
//readValue
      String json1 = "{\"name\":\"Василий\",\"salary\":125000}\n";
      EmployeeDTO employeeDTO1 = null;
      try {
          employeeDTO1 = objectMapper.readValue(json1, EmployeeDTO.class);
      } catch (JsonProcessingException e) {
          throw new RuntimeException(e);
      }
      System.out.println(employeeDTO1);
   }
}
