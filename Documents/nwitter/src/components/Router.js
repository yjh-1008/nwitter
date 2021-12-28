import React,{useState,useEffect} from 'react';
import { HashRouter as Router,Redirect,Route,Switch } from 'react-router-dom';
import Home from '../routes/Home';
import Auth from '../routes/Auth';
import Navigation from 'components/Navigation';
import Profile from 'routes/Profile';
const AppRouter=({isLoggedIn})=>{
    return(
        <Router>
            {isLoggedIn && <Navigation/>}
            <Switch>
                {isLoggedIn?(
                <>
                <Route exact path="/">
                    <Home/>
                </Route>
                    <Route exact path="/profile">
                        <Profile/>
                    </Route>
                    {/*<Redirect from="*" to="/"/>*/}
                </>):
                (
                <>
                    <Route exact path="/">
                        <Auth></Auth>
                    </Route>
                    {/*<Redirect from="*" to="/"/>*/}
                </>
                )}

            </Switch>

        </Router>
    );
};

export default AppRouter;