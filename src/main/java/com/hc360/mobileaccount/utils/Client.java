package com.hc360.mobileaccount.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client
{
  private String serviceURL = "http://sdk2.zucp.net:8060/webservice.asmx";

  private String sn = "";
  private String password = "";
  private String pwd = "";

  public Client(String sn, String password)
    throws UnsupportedEncodingException
  {
    this.sn = sn;
    this.password = password;
    this.pwd = getMD5(sn + password);
  }

  public String getMD5(String sourceStr)
    throws UnsupportedEncodingException
  {
    String resultStr = "";
    try {
      byte[] temp = sourceStr.getBytes();
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      md5.update(temp);

      byte[] b = md5.digest();
      for (int i = 0; i < b.length; i++) {
        char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', 
          '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] ob = new char[2];
        ob[0] = digit[(b[i] >>> 4 & 0xF)];
        ob[1] = digit[(b[i] & 0xF)];
        resultStr = resultStr + new String(ob);
      }
      return resultStr;
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }return null;
  }

  public String register(String province, String city, String trade, String entname, String linkman, String phone, String mobile, String email, String fax, String address, String postcode)
  {
    String result = "";
    String soapAction = "http://tempuri.org/Register";
    String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
    xml = xml + "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">";
    xml = xml + "<soap12:Body>";
    xml = xml + "<Register xmlns=\"http://tempuri.org/\">";
    xml = xml + "<sn>" + this.sn + "</sn>";
    xml = xml + "<pwd>" + this.password + "</pwd>";
    xml = xml + "<province>" + province + "</province>";
    xml = xml + "<city>" + city + "</city>";
    xml = xml + "<trade>" + trade + "</trade>";
    xml = xml + "<entname>" + entname + "</entname>";
    xml = xml + "<linkman>" + linkman + "</linkman>";
    xml = xml + "<phone>" + phone + "</phone>";
    xml = xml + "<mobile>" + mobile + "</mobile>";
    xml = xml + "<email>" + email + "</email>";
    xml = xml + "<fax>" + fax + "</fax>";
    xml = xml + "<address>" + address + "</address>";
    xml = xml + "<postcode>" + postcode + "</postcode>";
    xml = xml + "</Register>";
    xml = xml + "</soap12:Body>";
    xml = xml + "</soap12:Envelope>";
    try
    {
      URL url = new URL(this.serviceURL);

      URLConnection connection = url.openConnection();
      HttpURLConnection httpconn = (HttpURLConnection)connection;
      ByteArrayOutputStream bout = new ByteArrayOutputStream();
      bout.write(xml.getBytes());
      byte[] b = bout.toByteArray();
      httpconn.setRequestProperty("Content-Length", 
        String.valueOf(b.length));
      httpconn.setRequestProperty("Content-Type", 
        "text/xml; charset=gb2312");
      httpconn.setRequestProperty("SOAPAction", soapAction);
      httpconn.setRequestMethod("POST");
      httpconn.setDoInput(true);
      httpconn.setDoOutput(true);

      OutputStream out = httpconn.getOutputStream();
      out.write(b);
      out.close();

      InputStreamReader isr = new InputStreamReader(
        httpconn.getInputStream());
      BufferedReader in = new BufferedReader(isr);
      String inputLine;
      while ((inputLine = in.readLine()) != null)
      {
        Pattern pattern = 
          Pattern.compile("<RegisterResult>(.*)</RegisterResult>");
        Matcher matcher = pattern.matcher(inputLine);
        while (matcher.find()) {
          result = matcher.group(1);
        }
      }
      in.close();
      return new String(result.getBytes(), "utf-8");
    } catch (Exception e) {
      e.printStackTrace();
    }return "";
  }

  public String chargeFee(String cardno, String cardpwd)
  {
    String result = "";
    String soapAction = "http://tempuri.org/ChargUp";
    String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
    xml = xml + "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">";
    xml = xml + "<soap12:Body>";
    xml = xml + "<ChargUp xmlns=\"http://tempuri.org/\">";
    xml = xml + "<sn>" + this.sn + "</sn>";
    xml = xml + "<pwd>" + this.password + "</pwd>";
    xml = xml + "<cardno>" + cardno + "</cardno>";
    xml = xml + "<cardpwd>" + cardpwd + "</cardpwd>";
    xml = xml + "</ChargUp>";
    xml = xml + "</soap12:Body>";
    xml = xml + "</soap12:Envelope>";
    try
    {
      URL url = new URL(this.serviceURL);

      URLConnection connection = url.openConnection();
      HttpURLConnection httpconn = (HttpURLConnection)connection;
      ByteArrayOutputStream bout = new ByteArrayOutputStream();
      bout.write(xml.getBytes());
      byte[] b = bout.toByteArray();
      httpconn.setRequestProperty("Content-Length", 
        String.valueOf(b.length));
      httpconn.setRequestProperty("Content-Type", 
        "text/xml; charset=gb2312");
      httpconn.setRequestProperty("SOAPAction", soapAction);
      httpconn.setRequestMethod("POST");
      httpconn.setDoInput(true);
      httpconn.setDoOutput(true);

      OutputStream out = httpconn.getOutputStream();
      out.write(b);
      out.close();

      InputStreamReader isr = new InputStreamReader(
        httpconn.getInputStream());
      BufferedReader in = new BufferedReader(isr);
      String inputLine;
      while ((inputLine = in.readLine()) != null)
      {        
        Pattern pattern = 
          Pattern.compile("<ChargUpResult>(.*)</ChargUpResult>");
        Matcher matcher = pattern.matcher(inputLine);
        while (matcher.find()) {
          result = matcher.group(1);
        }
      }
      in.close();

      return new String(result.getBytes(), "utf-8");
    } catch (Exception e) {
      e.printStackTrace();
    }return "";
  }

  public String getBalance()
  {
    String result = "";
    String soapAction = "http://tempuri.org/balance";
    String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
    xml = xml + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
    xml = xml + "<soap:Body>";
    xml = xml + "<balance xmlns=\"http://tempuri.org/\">";
    xml = xml + "<sn>" + this.sn + "</sn>";
    xml = xml + "<pwd>" + this.pwd + "</pwd>";
    xml = xml + "</balance>";
    xml = xml + "</soap:Body>";
    xml = xml + "</soap:Envelope>";
    try
    {
      URL url = new URL(this.serviceURL);

      URLConnection connection = url.openConnection();
      HttpURLConnection httpconn = (HttpURLConnection)connection;
      ByteArrayOutputStream bout = new ByteArrayOutputStream();
      bout.write(xml.getBytes());
      byte[] b = bout.toByteArray();
      httpconn.setRequestProperty("Content-Length", 
        String.valueOf(b.length));
      httpconn.setRequestProperty("Content-Type", 
        "text/xml; charset=gb2312");
      httpconn.setRequestProperty("SOAPAction", soapAction);
      httpconn.setRequestMethod("POST");
      httpconn.setDoInput(true);
      httpconn.setDoOutput(true);

      OutputStream out = httpconn.getOutputStream();
      out.write(b);
      out.close();

      InputStreamReader isr = new InputStreamReader(
        httpconn.getInputStream());
      BufferedReader in = new BufferedReader(isr);
      String inputLine;
      while ((inputLine = in.readLine()) != null)
      {
        Pattern pattern = 
          Pattern.compile("<balanceResult>(.*)</balanceResult>");
        Matcher matcher = pattern.matcher(inputLine);
        while (matcher.find()) {
          result = matcher.group(1);
        }
      }
      in.close();
      return new String(result.getBytes());
    } catch (Exception e) {
      e.printStackTrace();
    }return "";
  }

  public String mt(String mobile, String content, String ext, String stime, String rrid)
  {
    String result = "";
    String soapAction = "http://tempuri.org/mt";
    String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
    xml = xml + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
    xml = xml + "<soap:Body>";
    xml = xml + "<mt xmlns=\"http://tempuri.org/\">";
    xml = xml + "<sn>" + this.sn + "</sn>";
    xml = xml + "<pwd>" + this.pwd + "</pwd>";
    xml = xml + "<mobile>" + mobile + "</mobile>";
    xml = xml + "<content>" + content + "</content>";
    xml = xml + "<ext>" + ext + "</ext>";
    xml = xml + "<stime>" + stime + "</stime>";
    xml = xml + "<rrid>" + rrid + "</rrid>";
    xml = xml + "</mt>";
    xml = xml + "</soap:Body>";
    xml = xml + "</soap:Envelope>";
    try
    {
      URL url = new URL(this.serviceURL);

      URLConnection connection = url.openConnection();
      HttpURLConnection httpconn = (HttpURLConnection)connection;
      ByteArrayOutputStream bout = new ByteArrayOutputStream();
      bout.write(xml.getBytes());
      byte[] b = bout.toByteArray();
      httpconn.setRequestProperty("Content-Length", 
        String.valueOf(b.length));
      httpconn.setRequestProperty("Content-Type", 
        "text/xml; charset=gb2312");
      httpconn.setRequestProperty("SOAPAction", soapAction);
      httpconn.setRequestMethod("POST");
      httpconn.setDoInput(true);
      httpconn.setDoOutput(true);

      OutputStream out = httpconn.getOutputStream();
      out.write(b);
      out.close();

      InputStreamReader isr = new InputStreamReader(
        httpconn.getInputStream());
      BufferedReader in = new BufferedReader(isr);
      String inputLine;
      while ((inputLine = in.readLine()) != null)
      {
        Pattern pattern = Pattern.compile("<mtResult>(.*)</mtResult>");
        Matcher matcher = pattern.matcher(inputLine);
        while (matcher.find()) {
          result = matcher.group(1);
        }
      }
      return result;
    } catch (Exception e) {
      e.printStackTrace();
    }return "";
  }

  public String mo()
  {
    String result = "";
    String soapAction = "http://tempuri.org/mo";
    String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
    xml = xml + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
    xml = xml + "<soap:Body>";
    xml = xml + "<mo xmlns=\"http://tempuri.org/\">";
    xml = xml + "<sn>" + this.sn + "</sn>";
    xml = xml + "<pwd>" + this.pwd + "</pwd>";
    xml = xml + "</mo>";
    xml = xml + "</soap:Body>";
    xml = xml + "</soap:Envelope>";
    try
    {
      URL url = new URL(this.serviceURL);

      URLConnection connection = url.openConnection();
      HttpURLConnection httpconn = (HttpURLConnection)connection;
      ByteArrayOutputStream bout = new ByteArrayOutputStream();
      bout.write(xml.getBytes());
      byte[] b = bout.toByteArray();
      httpconn.setRequestProperty("Content-Length", 
        String.valueOf(b.length));
      httpconn.setRequestProperty("Content-Type", 
        "text/xml; charset=gb2312");
      httpconn.setRequestProperty("SOAPAction", soapAction);
      httpconn.setRequestMethod("POST");
      httpconn.setDoInput(true);
      httpconn.setDoOutput(true);

      OutputStream out = httpconn.getOutputStream();
      out.write(b);
      out.close();

      InputStream isr = httpconn.getInputStream();
      StringBuffer buff = new StringBuffer();
      byte[] byte_receive = new byte[10240];
      for (int i = 0; (i = isr.read(byte_receive)) != -1; ) {
        buff.append(new String(byte_receive, 0, i));
      }
      isr.close();
      String result_before = buff.toString();
      int start = result_before.indexOf("<moResult>");
      int end = result_before.indexOf("</moResult>");
      result = result_before.substring(start + 10, end);

      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
    }return "";
  }

  public String msgid()
  {
    String result = "";
    String soapAction = "http://tempuri.org/msgid";
    String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
    xml = xml + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
    xml = xml + "<soap:Body>";
    xml = xml + "<msgid xmlns=\"http://tempuri.org/\">";
    xml = xml + "<sn>" + this.sn + "</sn>";
    xml = xml + "<pwd>" + this.pwd + "</pwd>";
    xml = xml + "</msgid>";
    xml = xml + "</soap:Body>";
    xml = xml + "</soap:Envelope>";
    try
    {
      URL url = new URL(this.serviceURL);

      URLConnection connection = url.openConnection();
      HttpURLConnection httpconn = (HttpURLConnection)connection;
      ByteArrayOutputStream bout = new ByteArrayOutputStream();
      bout.write(xml.getBytes());
      byte[] b = bout.toByteArray();
      httpconn.setRequestProperty("Content-Length", 
        String.valueOf(b.length));
      httpconn.setRequestProperty("Content-Type", 
        "text/xml; charset=gb2312");
      httpconn.setRequestProperty("SOAPAction", soapAction);
      httpconn.setRequestMethod("POST");
      httpconn.setDoInput(true);
      httpconn.setDoOutput(true);

      OutputStream out = httpconn.getOutputStream();
      out.write(b);
      out.close();

      InputStream isr = httpconn.getInputStream();
      StringBuffer buff = new StringBuffer();
      byte[] byte_receive = new byte[10240];
      for (int i = 0; (i = isr.read(byte_receive)) != -1; ) {
        buff.append(new String(byte_receive, 0, i));
      }
      isr.close();
      String result_before = buff.toString();
      int start = result_before.indexOf("<msgidResult>");
      int end = result_before.indexOf("</msgidResult>");
      result = result_before.substring(start + 13, end);
      return result;
    } catch (Exception e) {
      e.printStackTrace();
    }return "";
  }
}