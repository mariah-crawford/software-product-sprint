package com.google.sps.data;

/** A Message object containing the emailAddress and redirect url */
public final class Messages {
  
  private final String name;
  private final String comment;
  private final String img;

  public Messages(String name, String comment, String img) {
    this.name = name;
    this.comment = comment;
    this.img = img;
  }

}