package com.example.usermanagement.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.service.UserService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.ByteArrayOutputStream;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  public UserRepository userRepository;

  @Override
  public User storeUserDetails(String userName, String emailId , String phoneNumber , String password){
    User user = new User();
    user.setUserName(userName);
    user.setPassword(password);
    user.setEmailId(emailId);
    user.setPhoneNo(phoneNumber);
    user.setCreateddate(new Date());
    userRepository.save(user);
    return user;
  }

  @Override
  public List<User> getDetails(String typeOfSearch, String keyword, Integer page , Integer size){
    return userRepository.getDetailsFromKeyword(typeOfSearch,keyword, page, size);
  }

  @Override
  public byte[] getDownloadUser(String id)  {
    User user = userRepository.findById(id).get();
    try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
      Document document = new Document();
      PdfWriter.getInstance(document, byteArrayOutputStream);
      document.open();
      PdfPTable table = new PdfPTable(2); // 2 columns
      addTableCell(table, "KEY","VALUE");
      addTableCell(table, "Name of the User", user.getUserName());
      addTableCell(table, "Email Id", user.getEmailId());
      addTableCell(table, "Phone Number", user.getPhoneNo());
      addTableCell(table, "Created Date", user.getCreateddate().toString());
      document.add(table);
      document.close();
      return byteArrayOutputStream.toByteArray();
    }
    catch(DocumentException | IOException e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public List<User> getAllUserDetails(Integer page, Integer size){
  return userRepository.getAllUserDetails(page, size);
  }

  @Override
  public int getAllUsersCount(){
    return userRepository.findAll().size();
  }

  private static void addTableCell(PdfPTable table, String header, String value) {
    PdfPCell headerCell = new PdfPCell(new Phrase(header));
    PdfPCell valueCell = new PdfPCell(new Phrase(value));
    if (header.equals("KEY") || header.equals("VALUE")) {
      headerCell.setPadding(5.0f);
      headerCell.setBackgroundColor(new GrayColor(0.7f));
      headerCell.setPhrase(new Phrase(header, new Font(Font.FontFamily.HELVETICA, 12)));

      valueCell.setPadding(5.0f);
      valueCell.setGrayFill(15.0f);
      valueCell.setBackgroundColor(new GrayColor(0.7f));
      valueCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
      valueCell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
      valueCell.setPhrase(new Phrase(value, new Font(Font.FontFamily.HELVETICA, 12)));
    } else {
      headerCell.setPadding(5.0f);
      headerCell.setPaddingBottom(10.0f);
      valueCell.setPadding(5.0f);
      valueCell.setPaddingLeft(7.0f);
      valueCell.setPaddingBottom(10.0f);
    }
    headerCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
    headerCell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
    table.addCell(headerCell);
    table.addCell(valueCell);
  }
}
