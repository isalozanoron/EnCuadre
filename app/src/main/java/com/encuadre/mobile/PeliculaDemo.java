package com.encuadre.mobile;

/** Datos locales del prototipo. No consulta carteleras ni tráfico reales. */
public final class PeliculaDemo {
  public final String id, nombre, detalle, salida;
  public final int poster;

  private PeliculaDemo(String id, String nombre, String detalle, String salida, int poster) {
    this.id = id;
    this.nombre = nombre;
    this.detalle = detalle;
    this.salida = salida;
    this.poster = poster;
  }

  public static PeliculaDemo obtener(String id) {
    if ("obsession".equals(id))
      return new PeliculaDemo(
          "obsession",
          "Obsession",
          "Cinemark · Lun 1 sep · 20:30",
          "19:05",
          R.drawable.figma_obsession_detail);
    if ("robot".equals(id))
      return new PeliculaDemo(
          "robot",
          "Robot salvaje",
          "Cinépolis · Dom 13 sep · 20:30",
          "19:15",
          R.drawable.figma_robot_detail);
    return new PeliculaDemo(
        "odyssey",
        "The Odyssey",
        "Cine Colombia · Sáb 30 ago · 20:30",
        "19:15",
        R.drawable.figma_odyssey_detail);
  }
}
