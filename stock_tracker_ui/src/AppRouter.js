import React from 'react';
import { BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom';
import UserComponent from './UserComponent';
import StockComponent from './StockComponent';
import ProductComponent from './ProductComponent';

class AppRouter extends React.Component {
  render() {
    return (
      <Router>
        <nav>
          <ul>
            <li>
              <Link to="/users">Kullanıcılar</Link>
            </li>
            <li>
              <Link to="/stocks">Stoklar</Link>
            </li>
            <li>
              <Link to="/products">Ürünler</Link>
            </li>
          </ul>
        </nav>

        <Switch>
          <Route path="/users" component={UserComponent} />
          <Route path="/stocks" component={StockComponent} />
          <Route path="/products" component={ProductComponent} />
        </Switch>
      </Router>
    );
  }
}

export default AppRouter;
