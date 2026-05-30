import { Routes, Route, NavLink } from "react-router-dom";
import GroceryList from "./pages/GroceryList"; // Imports your new page layout

export default function App() {
  return (
    <div className="min-h-screen bg-[#F3F4F6] flex font-sans text-slate-800">
      
      {/* SIDEBAR */}
      <aside className="w-64 bg-white border-r border-slate-200 flex flex-col justify-between">
        <div>
          {/* Active Household Info */}
          <div className="p-6 border-b border-slate-100">
            <div className="flex items-center gap-3">
              <div className="h-10 w-10 bg-slate-900 rounded-full flex items-center justify-center text-white font-bold text-sm">
                T1
              </div>
              <div>
                <h2 className="font-bold text-slate-900 text-sm leading-tight">tirana1</h2>
                <p className="text-xs text-slate-400">erli.kulla</p>
              </div>
            </div>
            
            {/* Invite Code Pill */}
            <div className="mt-4 flex items-center justify-between bg-slate-50 border border-slate-200 rounded-lg px-3 py-1.5">
              <span className="text-xs font-mono font-bold text-slate-600">B1424U</span>
              <span className="text-xs text-slate-400 cursor-pointer hover:text-slate-600 select-none">📋</span>
            </div>
          </div>

          {/* Navigation Links */}
          <nav className="p-4 space-y-1">
            <NavItem to="/" icon="🏠" label="Home" />
            <NavItem to="/groceries" icon="🛒" label="Grocery List" />
            <NavItem to="/history" icon="⏳" label="History" />
            <NavItem to="/bills" icon="💵" label="Bills" />
          </nav>
        </div>

        {/* Logout Button */}
        <div className="p-4 border-t border-slate-100">
          <button className="flex items-center gap-3 text-slate-400 hover:text-slate-800 px-4 py-2 w-full transition font-bold text-xs tracking-wider">
            <span>↳</span>
            <span>LOGOUT</span>
          </button>
        </div>
      </aside>

      {/* MAIN CONTENT AREA */}
      <main className="flex-1 p-8">
        <Routes>
          <Route path="/" element={<HomeView />} />
          <Route path="/groceries" element={<GroceryList />} /> {/* Wired up perfectly! */}
          <Route path="/history" element={<div className="text-2xl font-bold text-slate-400 p-8 border-2 border-dashed rounded-xl">History View</div>} />
          <Route path="/bills" element={<div className="text-2xl font-bold text-slate-400 p-8 border-2 border-dashed rounded-xl">Bills View</div>} />
        </Routes>
      </main>
    </div>
  );
}

/* Sidebar Nav Link Wrapper Component */
function NavItem({ to, icon, label }) {
  return (
    <NavLink 
      to={to}
      className={({ isActive }) => 
        `flex items-center gap-3 px-4 py-3 rounded-lg font-bold text-sm transition ${
          isActive 
            ? "bg-slate-100 text-slate-900 border-r-4 border-slate-900" 
            : "text-slate-400 hover:bg-slate-50 hover:text-slate-700"
        }`
      }
    >
      <span className="text-base">{icon}</span>
      <span>{label}</span>
    </NavLink>
  );
}

/* Home view layout matching the dashboard cards */
function HomeView() {
  return (
    <div className="max-w-5xl mx-auto">
      <h1 className="text-2xl font-bold text-slate-900">Welcome back, erli.kulla!</h1>
      <p className="text-slate-400 text-sm mt-0.5">Here's what's happening in tirana1</p>
      
      {/* Metrics Row */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mt-8">
        <div className="bg-white p-6 rounded-xl border border-slate-200">
          <p className="text-xs font-bold uppercase tracking-wider text-slate-400">Active Items</p>
          <p className="text-3xl font-extrabold mt-1 text-slate-900">0</p>
        </div>
        <div className="bg-white p-6 rounded-xl border border-slate-200">
          <p className="text-xs font-bold uppercase tracking-wider text-slate-400">Total Spent</p>
          <p className="text-3xl font-extrabold mt-1 text-slate-900">0 ALL</p>
        </div>
        <div className="bg-white p-6 rounded-xl border border-slate-200">
          <p className="text-xs font-bold uppercase tracking-wider text-slate-400">Your Balance</p>
          <p className="text-3xl font-extrabold mt-1 text-slate-900">0 ALL</p>
        </div>
      </div>
    </div>
  );
}