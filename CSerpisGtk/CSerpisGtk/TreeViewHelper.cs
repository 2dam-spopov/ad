using System;
using Gtk;
using System.Collections;

namespace Serpis.Ad {

    public class TreeViewHelper {
        public static void Fill(TreeView treeView, string[] propertyNames, IEnumerable enumerable) {
            CellRendererText cellRendererText = new CellRendererText();
            foreach (string propertyName in propertyNames) {
                treeView.AppendColumn(propertyName, cellRendererText,
                delegate (TreeViewColumn tree_column, CellRenderer cell, TreeModel tree_model, TreeIter iter) {
                    object model = tree_model.GetValue(iter, 0);
                    object value = model.GetType().GetProperty(propertyName).GetValue(model);
                    cellRendererText.Text = value + "";
                });

            }
            ListStore listStore = new ListStore(typeof(object));
            treeView.Model = listStore;
            foreach (object obj in enumerable) {
                listStore.AppendValues(obj);
            }
        }
    }
}