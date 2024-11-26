package thesisman.client.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class RestDriver {
  private final HttpClient client;

  public RestDriver() {
    this.client = HttpClientBuilder.create().build();
  }

  public boolean submeteFinal(int id, File file) {
    String uri = "http://localhost:8080/api/teses/final/" + id;
    System.out.println(file.length());
    try {
      HttpPost request = new HttpPost(uri);

      String boundary = "===" + System.currentTimeMillis() + "===";
      request.setHeader("Content-Type", "multipart/form-data; boundary=" + boundary);

      HttpEntity entity = createMultipartEntity(boundary, file);
      request.setEntity(entity);
      System.out.println(convertStreamToString(entity.getContent()));

      HttpResponse response = client.execute(request);

      if (response.getEntity() != null && response.getStatusLine().getStatusCode() == 200) {
        String result = EntityUtils.toString(response.getEntity());
        return !result.isBlank();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public boolean submeteProposta(int id, File file) {
    String uri = "http://localhost:8080/api/teses/proposta/" + id;
    System.out.println(file.length());
    try {
      HttpPost request = new HttpPost(uri);

      String boundary = "===" + System.currentTimeMillis() + "===";
      request.setHeader("Content-Type", "multipart/form-data; boundary=" + boundary);

      HttpEntity entity = createMultipartEntity(boundary, file);
      request.setEntity(entity);
      System.out.println(convertStreamToString(entity.getContent()));

      HttpResponse response = client.execute(request);

      if (response.getEntity() != null && response.getStatusLine().getStatusCode() == 200) {
        String result = EntityUtils.toString(response.getEntity());
        return !result.isBlank();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  private HttpEntity createMultipartEntity(String boundary, File file) throws IOException {
    String twoHyphens = "--";
    String lineEnd = "\r\n";

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    DataOutputStream writer = new DataOutputStream(outputStream);

    // Adicionar arquivo
    writer.writeBytes(twoHyphens + boundary + lineEnd);
    writer.writeBytes(
        "Content-Disposition: form-data; name=\"file\"; filename=\""
            + file.getName()
            + "\""
            + lineEnd);
    writer.writeBytes("Content-Type: " + ContentType.DEFAULT_BINARY + lineEnd);
    writer.writeBytes(lineEnd);

    FileInputStream fileInputStream = new FileInputStream(file);
    byte[] buffer = new byte[4096];
    int bytesRead;
    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
      writer.write(buffer, 0, bytesRead);
    }
    fileInputStream.close();
    writer.writeBytes(lineEnd);

    // Terminar parte do arquivo
    writer.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
    writer.flush();
    writer.close();

    return new ByteArrayEntity(outputStream.toByteArray(), ContentType.MULTIPART_FORM_DATA);
  }

  public List<Candidatura> getCandidaturasData(int id) {
    String uri = "http://localhost:8080/api/alunos/" + id + "/candidaturas";
    List<Candidatura> candidaturas = new ArrayList<>();
    try {
      // executa o pedido http
      HttpGet request = new HttpGet(uri);
      HttpResponse response = client.execute(request);
      HttpEntity entity = response.getEntity();
      if (entity != null && response.getStatusLine().getStatusCode() == 200) {
        // converte a resposta num json
        InputStream instream = entity.getContent();
        String result = convertStreamToString(instream);
        JsonNode rootArray = new ObjectMapper().readTree(result);

        if (rootArray.isArray()) {
          for (JsonNode jsonObject : rootArray) {
            Candidatura candidatura = convertJSONtoCandidatura(jsonObject);
            candidaturas.add(candidatura);
            System.out.println(candidatura);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return candidaturas;
  }

  public Tese getTese(int id) {
    String uri = "http://localhost:8080/api/teses/" + id;
    Tese t = null;
    try {
      // executa o pedido http
      HttpGet request = new HttpGet(uri);
      HttpResponse response = client.execute(request);
      HttpEntity entity = response.getEntity();
      if (entity != null && response.getStatusLine().getStatusCode() == 200) {
        // converte a resposta num json
        InputStream instream = entity.getContent();
        String result = convertStreamToString(instream);
        JsonNode jsonObject = new ObjectMapper().readTree(result);
        t = convertJSONtoTese(jsonObject);
      } else {
        System.out.println("null");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return t;
  }

  private Tese convertJSONtoTese(JsonNode jsonObject) {
    int id = jsonObject.get("id").asInt();
    boolean b = jsonObject.get("projeto").asBoolean();

    return new Tese(id, b);
  }

  private Candidatura convertJSONtoCandidatura(JsonNode jsonObject) {
    int id = jsonObject.get("id").asInt();
    JsonNode temaJson = jsonObject.get("tema");
    Tema tema = convertJSONtoTema(temaJson);
    String titulo = tema.getTitulo();
    Double remuneracao = tema.getRemuneracao();
    String estado = jsonObject.get("estado").asText();

    return new Candidatura(id, titulo, remuneracao, estado);
  }

  public Aluno getAlunoData(String email) {
    String uri = "http://localhost:8080/api/login?email=" + email.trim();
    Aluno a = null;
    try {
      // executa o pedido http
      HttpGet request = new HttpGet(uri);
      HttpResponse response = client.execute(request);
      HttpEntity entity = response.getEntity();
      if (entity != null && response.getStatusLine().getStatusCode() == 200) {
        // converte a resposta num json
        InputStream instream = entity.getContent();
        String result = convertStreamToString(instream);
        JsonNode jsonObject = new ObjectMapper().readTree(result);
        a = convertJSONtoAluno(jsonObject);
      } else {
        System.out.println("null");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return a;
  }

  private static Aluno convertJSONtoAluno(JsonNode jsonObject) {
    int id = jsonObject.get("id").asInt();
    String emailField = jsonObject.get("email").asText();
    String name = jsonObject.get("name").asText();
    String surname = jsonObject.get("surname").asText();
    double media = jsonObject.get("media").asDouble();
    String mestrado = jsonObject.get("mestrado").asText();
    return new Aluno(id, name, surname, emailField, media, mestrado);
  }

  public List<Tema> getTemasData() {
    String uri = "http://localhost:8080/api/temas";
    List<Tema> temas = new ArrayList<>();
    try {
      // executa o pedido http
      HttpGet request = new HttpGet(uri);
      HttpResponse response = client.execute(request);
      HttpEntity entity = response.getEntity();
      if (entity != null && response.getStatusLine().getStatusCode() == 200) {
        // converte a resposta num json
        InputStream instream = entity.getContent();
        String result = convertStreamToString(instream);
        JsonNode rootArray = new ObjectMapper().readTree(result);

        if (rootArray.isArray()) {
          for (JsonNode jsonObject : rootArray) {
            Tema tema = convertJSONtoTema(jsonObject);
            temas.add(tema);
            System.out.println(tema);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return temas;
  }

  private static Tema convertJSONtoTema(JsonNode jsonObject) {
    int id = jsonObject.get("id").asInt();
    String titulo = jsonObject.get("titulo").asText();
    String descricao = jsonObject.get("descricao").asText();
    double remuneracao = jsonObject.get("remuneracao").asDouble();

    List<String> mestradosCompativeis = new ArrayList<>();
    JsonNode mestradosArray = jsonObject.get("mestradosCompativeis");
    if (mestradosArray != null && mestradosArray.isArray()) {
      for (JsonNode mestradoNode : mestradosArray) {
        mestradosCompativeis.add(mestradoNode.asText());
      }
    }

    ObservableList<String> mestradosObservableList =
        FXCollections.observableArrayList(mestradosCompativeis);

    return new Tema(id, titulo, remuneracao, mestradosObservableList, descricao);
  }

  public boolean candidata(int aluno, int tema) {
    String uri = "http://localhost:8080/api/alunos/" + aluno + "/" + tema;
    try {
      // executa o pedido http
      HttpPatch request = new HttpPatch(uri);
      HttpResponse response = client.execute(request);
      HttpEntity entity = response.getEntity();
      if (entity != null && response.getStatusLine().getStatusCode() == 200) {
        InputStream instream = entity.getContent();
        String result = convertStreamToString(instream);
        if (!result.isBlank()) {
          return true;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public boolean cancela(int aluno, int candidatura) {
    String uri = "http://localhost:8080/api/alunos/" + aluno + "/" + candidatura;
    try {
      // executa o pedido http
      HttpDelete request = new HttpDelete(uri);
      HttpResponse response = client.execute(request);
      HttpEntity entity = response.getEntity();
      if (entity != null && response.getStatusLine().getStatusCode() == 200) {
        InputStream instream = entity.getContent();
        String result = convertStreamToString(instream);
        if (!result.isBlank()) {
          return true;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  private static String convertStreamToString(InputStream is) {
    try (java.util.Scanner s = new java.util.Scanner(is)) {
      s.useDelimiter("\\A");
      return s.hasNext() ? s.next() : "";
    }
  }
}
