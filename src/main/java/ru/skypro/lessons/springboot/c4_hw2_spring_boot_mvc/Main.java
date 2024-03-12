package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.DTO.EmployeeDTO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public class Main {
  public static void main(String[] args) throws JsonProcessingException {

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
      //String json1 = "{\"name\":\"Василий\",\"salary\":125000}\n";
      String json1 = json;
      EmployeeDTO employeeDTO1 = null;
      try {
          employeeDTO1 = objectMapper.readValue(json1, EmployeeDTO.class);
      } catch (JsonProcessingException e) {
          throw new RuntimeException(e);
      }
      System.out.println(employeeDTO1);

      EmployeeDTO employee1 = new EmployeeDTO(1, "Ivan", 10000);
      EmployeeDTO employee2 = new EmployeeDTO(2, "Ivan", 10000);
      List<EmployeeDTO> employees = List.of(employee1, employee2);
      ByteArrayInputStream inputStream = new ByteArrayInputStream(objectMapper.writeValueAsBytes(employees));
      for(byte a : inputStream.readAllBytes()){
          System.out.print(a +" ");
      }
      System.out.println();
      try {
          inputStream.close();
      } catch (IOException e) {
          throw new RuntimeException(e);
      }
String str = employees.toString();
      System.out.println("_______________");
      System.out.println(str);

  }
   // TODO test example


}
