import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import UserList from './components/user/userList';
import UserDetail from './components/user/userDetail';
import UserCreate from './components/user/userCreate';
import UserUpdate from './components/user/userUpdate';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<UserList />} />
        <Route path="/user/create" element={<UserCreate />} />
        <Route path="/user/update/:id" element={<UserUpdate />} />
        <Route path="/user/:id" element={<UserDetail />} />
      </Routes>
    </Router>
  );
}

export default App;
