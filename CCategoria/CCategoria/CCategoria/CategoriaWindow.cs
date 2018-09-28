using System;
namespace CCategoria
{
    public partial class CategoriaWindow : Gtk.Window
    {
        public CategoriaWindow() :
                base(Gtk.WindowType.Toplevel)
        {
            this.Build();
			buttonSave.Click+=delegate {

			};
        }

		protected void OnButtonSaveClicked(object sender, EventArgs e){
			Console.WriteLine("Nombre=" + entryNombre1.Text);
		}
	}
}
