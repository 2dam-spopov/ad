using System;
using Gtk;
using Serpis.Ad;
using Serpis.Ad.Ventas;
public partial class MainWindow : Gtk.Window {
    public MainWindow() : base(Gtk.WindowType.Toplevel) {
        Build();
        EntityDao<Articulo> articuloDao = new EntityDao<Articulo>();
        TreeViewHelper.Fill(treeview, new string[] { "Id", "Nombre","Precio" },articuloDao.Enumerable);
    }

    protected void OnDeleteEvent(object sender, DeleteEventArgs a) {
        Application.Quit();
        a.RetVal = true;
    }
}
