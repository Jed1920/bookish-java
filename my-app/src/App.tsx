import React, { useState } from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import logo from './logo.svg';
import './App.scss';
import {Book} from './Books'
import {Author} from './Author'
import { CreateUser } from './CreateUser';
import { Login } from './Login';

const App: React.FC = () => {
  const [authenticated, setAuthenticated] = useState(false)
  const [author,setAuthor] = useState("")
  console.log(author)
    return (
      <div className="App">
        <Router >
          <Route path="/login">
            <Login authentication = {authenticated} setAuthentication = {setAuthenticated}/>
          </Route>
          <Route path="/create_user">
            <CreateUser authentication = {authenticated} setAuthentication = {setAuthenticated}/>
          </Route>
          <Route path="/books">
            <Book authentication = {authenticated} setAuthor = {setAuthor}/>
          </Route>
          <Route path="/author/">
            <Author authentication = {authenticated} author = {author}/>
          </Route>
        </Router>
      </div>
    );
}

export default App;

