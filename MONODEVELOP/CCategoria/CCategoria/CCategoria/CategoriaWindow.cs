using System;
using System.Data;

using Serpis.Ad.Ventas;
namespace CCategoria
{
    public partial class CategoriaWindow : Gtk.Window
    {
        public CategoriaWindow(Categoria categoria) :
                base(Gtk.WindowType.Toplevel)
        {
            this.Build();
            entryNombre.Text = categoria.Nombre;
			buttonSave.Clicked+=delegate {
                categoria.Nombre = entryNombre.Text;
                CategoriaDao.Save(categoria);
			};
        }

	}
}
