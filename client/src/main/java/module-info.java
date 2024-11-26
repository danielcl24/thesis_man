module thesisman.client {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.web;
  requires org.controlsfx.controls;
  requires com.dlsc.formsfx;
  requires net.synedra.validatorfx;
  requires org.kordamp.ikonli.javafx;
  requires org.kordamp.bootstrapfx.core;
  requires eu.hansolo.tilesfx;
  requires java.net.http;
  requires org.apache.httpcomponents.httpclient;
  requires org.apache.httpcomponents.httpcore;
  requires jdk.jsobject;
  requires com.fasterxml.jackson.databind;
  requires com.fasterxml.jackson.core;
  requires java.naming;

  opens thesisman.client to
      javafx.fxml;

  exports thesisman.client;
  exports thesisman.client.controllers;
  exports thesisman.client.models;
  exports thesisman.client.views;
}
