using Gtk;
using System;
using System.Data;
using CCategoria;
using Serpis.Ad;
using System.Reflection;


public partial class MainWindow : Gtk.Window{


        public MainWindow() : base(Gtk.WindowType.Toplevel){
        Build();
        Title = "Categoría";

		App.Instance.DbConnection.Open();

             
		TreeViewHelper.Fill(treeView, new string[] {"Id", "Nombre"}, CategoriaDao.Categorias);
        
        newAction.Activated+= delegate {
            new CategoriaWindow(new Categoria());
        };
        editAction.Activated+=delegate {
            
            object id = TreeViewHelper.GetId(treeView);
            Console.WriteLine("Id=" + id);
            Categoria categoria = CategoriaDao.Load(id);
            new CategoriaWindow(categoria);

		};


        deleteAction.Activated+=delegate {
        
            object id = TreeViewHelper.GetId(treeView);
            if(WindowHelper.Confirm(this, "¿Eliminar?")){
                
            }
        };



        refreshAction.Activated+=delegate {
            TreeViewHelper.Fill(treeView, new string[] { "Id", "Nombre" }, CategoriaDao.Categorias);
        } ;


        treeView.Selection.Changed+=delegate {
			refreshUI();
        };

        refreshUI();
    }


	private void refreshUI(){
		bool treeViewIsSelected = treeView.Selection.CountSelectedRows() > 0;
		editAction.Sensitive = treeViewIsSelected;
		deleteAction.Sensitive = treeViewIsSelected;
	}


	private void insert(){
		IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
		dbCommand.CommandText = "insert into categoria (Nombre) values ('categoria4')";
		int numeroFilas = dbCommand.ExecuteNonQuery();
	}
	private void update() {
        IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
        dbCommand.CommandText = "update categoria set nombre='categoria 4 modificada' where id=4";
        dbCommand.ExecuteNonQuery();
    }
	private void update(Categoria categoria){
		IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
        dbCommand.CommandText = "update categoria set nombre=@nombre where id=@id";
        DbCommandHelper.AddParameter(dbCommand, "nombre", categoria.Nombre);
        DbCommandHelper.AddParameter(dbCommand, "id", categoria.Id);
        dbCommand.ExecuteNonQuery();
    }
	private void delete() {
		IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
        dbCommand.CommandText = "delete from categoria where id=4";
        dbCommand.ExecuteNonQuery();
    }


    protected void OnDeleteEvent(object sender, DeleteEventArgs a)  {
		Application.Quit();
        a.RetVal = true;
    }
}
