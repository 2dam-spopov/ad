using System;
using MySql.Data.MySqlClient;


namespace CMysql
{
    class MainClass
    {
        public static void Main(string[] args)
        {
			MySqlConnection mySqlConnection = new MySqlConnection("Server=127.0.0.1;Database=dbprueba;UserID=root;Password=sistemas");
			mySqlConnection.Open();
			mySqlConnection.Close();
		}
    }
    
}
