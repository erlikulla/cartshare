import { useState } from "react";

export default function GroceryList() {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [itemName, setItemName] = useState("");
  
  // Local state array to hold added items dynamically
  const [items, setItems] = useState([]);

  const handleSaveItem = () => {
    const newItem = {
      id: Date.now(), // Temporary ID until database takes over
      name: itemName,
      addedBy: "erli.kulla", // Logged-in user tracking
      isPurchased: false,
      claimedBy: null
    };

    setItems([...items, newItem]);
    setIsModalOpen(false);
    setItemName("");
  };

  // Local handler to remove an item from our temporary list
  const handleDeleteItem = (idToDelete) => {
    setItems(items.filter(item => item.id !== idToDelete));
  };

  return (
    <div className="max-w-5xl mx-auto relative">
      {/* Header Area */}
      <div className="flex justify-between items-center mb-8">
        <div>
          <h1 className="text-2xl font-bold text-slate-900">Grocery List</h1>
          <p className="text-slate-400 text-sm mt-0.5">{items.length} {items.length === 1 ? 'item' : 'items'} to buy</p>
        </div>
        <button 
          onClick={() => setIsModalOpen(true)}
          className="bg-indigo-600 hover:bg-indigo-700 text-white px-4 py-2 rounded-lg font-medium transition flex items-center gap-2"
        >
          <span className="text-xl leading-none">+</span> ADD ITEM
        </button>
      </div>

      {/* Conditional Rendering: Empty State vs Active List */}
      {items.length === 0 ? (
        <div className="bg-white border border-slate-200 rounded-xl p-12 flex flex-col items-center justify-center text-center">
          <div className="text-5xl mb-4 opacity-50">🛒</div>
          <h3 className="text-lg font-bold text-slate-800">No items yet</h3>
          <p className="text-slate-500 mt-1 text-sm">Click "Add Item" to start building your grocery list</p>
        </div>
      ) : (
        <div className="bg-white border border-slate-200 rounded-xl divide-y divide-slate-100 overflow-hidden">
          {items.map((item) => (
            <div key={item.id} className="p-4 flex items-center justify-between hover:bg-slate-50 transition">
              <div>
                <h4 className="font-bold text-slate-800 text-base">{item.name}</h4>
                <p className="text-xs text-slate-400 mt-0.5">Added by {item.addedBy}</p>
              </div>
              
              <div className="flex items-center gap-3">
                <button className="border border-slate-200 hover:bg-slate-100 font-bold text-xs text-slate-600 px-3 py-1.5 rounded-md transition tracking-wider">
                  I'VE GOT IT!
                </button>
                {/* Delete Button */}
                <button 
                  onClick={() => handleDeleteItem(item.id)}
                  className="border border-red-200 hover:bg-red-50 font-bold text-xs text-red-600 px-3 py-1.5 rounded-md transition tracking-wider"
                >
                  DELETE
                </button>
              </div>
            </div>
          ))}
        </div>
      )}

      {/* Add Item Modal */}
      {isModalOpen && (
        <div className="fixed inset-0 bg-slate-900/50 flex items-center justify-center z-50">
          <div className="bg-white rounded-xl shadow-xl w-full max-w-md overflow-hidden">
            <div className="px-6 py-4 border-b border-slate-100 bg-slate-50/50">
              <h3 className="font-bold text-slate-800">Add Grocery Item</h3>
            </div>
            
            <div className="p-6 space-y-4">
              <div>
                <label className="block text-xs font-bold text-slate-500 uppercase tracking-wider mb-2">Item Name *</label>
                <input 
                  type="text" 
                  autoFocus
                  placeholder="e.g., Buka, Qumesht, Veze" 
                  value={itemName}
                  onChange={(e) => setItemName(e.target.value)}
                  className="w-full border border-slate-300 rounded-lg px-4 py-2.5 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500"
                />
              </div>
            </div>

            <div className="px-6 py-4 bg-slate-50 flex justify-end gap-3 border-t border-slate-100">
              <button 
                onClick={() => setIsModalOpen(false)}
                className="px-4 py-2 text-slate-500 hover:text-slate-700 font-medium transition"
              >
                CANCEL
              </button>
              <button 
                onClick={handleSaveItem}
                disabled={!itemName.trim()}
                className="bg-indigo-600 hover:bg-indigo-700 disabled:bg-indigo-300 text-white px-6 py-2 rounded-lg font-medium transition"
              >
                ADD ITEM
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}