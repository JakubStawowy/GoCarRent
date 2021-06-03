import React from 'react';
import {AppBar, Typography, Button, Grid, Box, Avatar} from "@material-ui/core";
import {NavLink} from "react-router-dom";
import PostAddIcon from '@material-ui/icons/PostAdd';
import HomeOutlinedIcon from '@material-ui/icons/HomeOutlined';
import AirportShuttleIcon from '@material-ui/icons/AirportShuttle';
import ExitToAppIcon from '@material-ui/icons/ExitToApp';
import logoImage from '../uploads/logo.png';
import {logoutUser} from "../actions/actionRepository";
import {useDispatch, useSelector} from "react-redux";

import {useState} from "react";
import {useLeftSideBarStyles} from "../style/LeftSideBarStyles";
import {useSideBarStyles} from "../style/SideBarStyles";

export default function LeftSidebar(props) {

    const classes = useLeftSideBarStyles();
    const sideBarClasses = useSideBarStyles();

    const dispatch = useDispatch();

    const [isLogged, setIsLogged] = useState(useSelector(state => state.isLogged));

    const handleNavLinkClick = (e) => {
        if (localStorage.getItem('token') === null) {
            e.preventDefault();
            alert("You don't have access to resources. You must be logged in");
        }
    }

    const handleLogout = () => {
        dispatch(logoutUser({
            userId: localStorage.getItem('userId')
        }));
        setIsLogged(!isLogged);
    };

    return (
        <Grid item xs className={props.sideBarStatus && sideBarClasses.sideBar}>
            <AppBar className={classes.bar} position={'relative'}>
                <NavLink to={'/home'} onClick={(e)=>handleNavLinkClick(e)}>
                    <Box className={classes.circle}>
                        <Avatar src={logoImage} alt={''} className={classes.logo}/>
                    </Box>
                </NavLink>
                <NavLink className={classes.navLink} to={"/add"} onClick={(e)=>handleNavLinkClick(e)}>
                    <Button className={classes.button} size={'large'} id={"addButton"}>
                        <PostAddIcon />
                        <Typography variant={"button"} className={classes.label}>add</Typography>
                    </Button>
                </NavLink>
                <NavLink className={classes.navLink} to={"/home"} onClick={(e)=>handleNavLinkClick(e)}>
                    <Button className={classes.button} size={'large'}>
                        <HomeOutlinedIcon />
                        <Typography variant={"BUTTON"} className={classes.label}>home</Typography>
                    </Button>
                </NavLink>
                <NavLink className={classes.navLink} to={"/user/{id}/rented"} onClick={(e)=>handleNavLinkClick(e)}>
                    <Button className={classes.button} size={'large'}>
                        <AirportShuttleIcon />
                        <Typography variant={"BUTTON"} className={classes.label}>rented cars</Typography>
                    </Button>
                </NavLink>
                <NavLink className={classes.navLink} to={"/login"} onClick={(e)=>handleNavLinkClick(e)}>
                    {isLogged.logged ? (
                        <Button className={classes.button} size={'large'} id={"logoutButton"} onClick={handleLogout}>
                            <ExitToAppIcon />
                            <Typography variant={"BUTTON"} className={classes.label}>logout</Typography>
                        </Button>
                    ) : (
                        <Button className={classes.button} size={'large'} id={"logoutButton"} onClick={handleLogout}>
                            <ExitToAppIcon />
                            <Typography variant={"BUTTON"} className={classes.label}>login</Typography>
                        </Button>
                    )}
                </NavLink>
            </AppBar>
        </Grid>
    );
};