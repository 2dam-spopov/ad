using System;
using Gtk;
using MySql.Data.MySqlClient;
using Serpis.Ad;

namespace CCategoria
{
    class MainClass
    {
        public static void Main(string[] args)
        {
            App.Instance.DbConnection = new MySqlConnection("server=localhost;database=dbprueba;user=root;password=sistemas;ssl-mode=none");

            Application.Init();
            MainWindow win = new MainWindow();
            win.Show();
            Application.Run();
            App.Instance.DbConnection.Close();

        }

    }
}
