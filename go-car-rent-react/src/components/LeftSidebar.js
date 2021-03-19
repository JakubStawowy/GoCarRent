import React, {Component} from 'react';
import {AppBar, makeStyles, Typography, Button, Badge, Grid} from "@material-ui/core";
import {NavLink} from "react-router-dom";
import AddIcon from '@material-ui/icons/Add';
import HomeOutlinedIcon from '@material-ui/icons/HomeOutlined';
import AirportShuttleIcon from '@material-ui/icons/AirportShuttle';
import ExitToAppIcon from '@material-ui/icons/ExitToApp';
import logoImage from '../uploads/graylogo2.png';

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
    }
}));

export default function LeftSidebar() {
    const classes = useStyles();
    return (
        <Grid item xs>
            <AppBar className={classes.bar} position={'relative'}>
                <NavLink to={'/home'}>
                    <div className={classes.circle}>
                        <img src={logoImage} alt={''} style={{width: '100%', objectFit: 'cover'}}/>
                    </div>
                </NavLink>
                <NavLink to={"/add"}>
                    <Button className={classes.button} size={'large'}>
                        <AddIcon />
                        <Typography variant={"button"}>add</Typography>
                    </Button>
                </NavLink>
                <NavLink to={"/home"}>
                    <Button className={classes.button} size={'large'}>
                        <HomeOutlinedIcon />
                        <Typography variant={"BUTTON"}>home</Typography>
                    </Button>
                </NavLink>
                <NavLink to={"/cars/rented"}>
                    <Button className={classes.button} size={'large'}>
                        <AirportShuttleIcon />
                        <Typography variant={"BUTTON"}>rented cars</Typography>
                    </Button>
                </NavLink>
                <NavLink to={"/login"}>
                    <Button className={classes.button} size={'large'}>
                        <ExitToAppIcon />
                        <Typography variant={"BUTTON"}>logout</Typography>
                    </Button>
                </NavLink>
            </AppBar>
        </Grid>
    );
};