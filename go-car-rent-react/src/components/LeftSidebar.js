import React, {useEffect} from 'react';
import {AppBar, makeStyles, Typography, Button, Grid, Box, Avatar} from "@material-ui/core";
import {NavLink} from "react-router-dom";
import PostAddIcon from '@material-ui/icons/PostAdd';
import HomeOutlinedIcon from '@material-ui/icons/HomeOutlined';
import AirportShuttleIcon from '@material-ui/icons/AirportShuttle';
import ExitToAppIcon from '@material-ui/icons/ExitToApp';
import logoImage from '../uploads/logo.png';
import {logoutUser} from "../actions";
import {useDispatch, useSelector} from "react-redux";

import {useState} from "react";

const useStyles = makeStyles((theme) => ({
    bar: {
        background: 'transparent linear-gradient(180deg, #4FC7C3E0 0%, #4BBEBAE0 72%, #286462E0 100%) 0% 0% no-repeat padding-box',
        height: '100%',
        position: 'relative',
        justifyContent: 'space-around',
        boxShadow: 'none',
        paddingLeft: '3em',
    },
    button: {
        width: '100%',
        background: 'white',
        borderRadius: '30px 0 0 30px',
        color: '#4BBEBAE0',
        '&:hover': {
            color: 'white'
        }
    },
    circle: {
        width: '10vw',
        height: '10vw',
        borderRadius: '50%',
        background: "white",
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center'
    },
    logo: {
        width: '100%',
        height: '100%'
    }
}));

export default function LeftSidebar() {
    const classes = useStyles();
    const dispatch = useDispatch();

    const [isLogged, setIsLogged] = useState(useSelector(state => state.isLogged));

    const handleLogout = () => {
        dispatch(logoutUser({
            userId: localStorage.getItem('userId')
        }));
        setIsLogged(!isLogged);
    };

    return (
        <Grid item xs>
            <AppBar className={classes.bar} position={'relative'}>
                <NavLink to={'/home'}>
                    <Box className={classes.circle}>
                        <Avatar src={logoImage} alt={''} className={classes.logo}/>
                    </Box>
                </NavLink>
                <NavLink to={"/add"}>
                    <Button className={classes.button} size={'large'} id={"addButton"}>
                        <PostAddIcon />
                        <Typography variant={"button"}>add</Typography>
                    </Button>
                </NavLink>
                <NavLink to={"/home"}>
                    <Button className={classes.button} size={'large'}>
                        <HomeOutlinedIcon />
                        <Typography variant={"BUTTON"}>home</Typography>
                    </Button>
                </NavLink>
                <NavLink to={"/user/{id}/rented"}>
                    <Button className={classes.button} size={'large'}>
                        <AirportShuttleIcon />
                        <Typography variant={"BUTTON"}>rented cars</Typography>
                    </Button>
                </NavLink>
                <NavLink to={"/login"}>
                    {isLogged.logged ? (
                        <Button className={classes.button} size={'large'} id={"logoutButton"} onClick={handleLogout}>
                            <ExitToAppIcon />
                            <Typography variant={"BUTTON"}>logout</Typography>
                        </Button>
                    ) : (
                        <Button className={classes.button} size={'large'} id={"logoutButton"} onClick={handleLogout}>
                            <ExitToAppIcon />
                            <Typography variant={"BUTTON"}>login</Typography>
                        </Button>
                    )}
                </NavLink>
            </AppBar>
        </Grid>
    );
};