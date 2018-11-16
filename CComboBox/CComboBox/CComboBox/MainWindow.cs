﻿using System;
using Gtk;

public partial class MainWindow : Gtk.Window {
    public MainWindow() : base(Gtk.WindowType.Toplevel) {
        Build();

        //var cellRendererText = new CellRendererText();
        //comboBox.PackStart(cellRendererText, expand: false);
        //comboBox.AddAttribute(cellRendererText, "text",0);
        var labelRendererText = new CellRendererText();
        comboBox.PackStart(labelRendererText, expand: false);
        comboBox.AddAttribute(labelRendererText, "text", 1);

        var list = new[]{
            new {Id=1,Nombre="cat1"},
            new {Id=2,Nombre="cat2"},
            new {Id=3,Nombre="cat3"}
        };

        int? initialId = null;

        ListStore listStore = new ListStore(typeof(int?),typeof(string));
        TreeIter initialTreeIter=listStore.AppendValues(null, "<sin asignar>");
        foreach (var item in list) {
            TreeIter treeIter = listStore.AppendValues(item, item.Nombre);
            if(item.Id==initialId){
                initialTreeIter = treeIter;
            }
        }
        comboBox.Model = listStore;
        comboBox.SetActiveIter(initialTreeIter);
    }

    protected void OnDeleteEvent(object sender, DeleteEventArgs a) {
        Application.Quit();
        a.RetVal = true;
    }
}