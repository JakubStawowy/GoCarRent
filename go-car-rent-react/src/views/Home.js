import React from 'react';
import '../components/components.css';
import {Button, Container, Fab, Grid, List, ListItem, makeStyles, Typography} from "@material-ui/core";
import Advertisement from "../components/Advertisement";
import SearchIcon from '@material-ui/icons/Search';
import TuneIcon from '@material-ui/icons/Tune';
import cars from '../data/cars';

const useStyles = makeStyles((theme) => ({
    list: {
        maxHeight: '72vh',
        marginTop: '2em',
        overflow: 'auto'
    },
    container: {marginTop: '2em'},
    buttonContainer: {
        display: "flex",
        justifyContent: "space-around",
    },
    button: {
        background: '#4BBEBAE0',
        width: '40%',
    }
}));

export default function Home() {
    const classes = useStyles();
    const carData = cars;
    return (
            <Container className={classes.container}>
                <Container className={classes.buttonContainer}>
                    <Fab variant={'extended'} className={classes.button}>
                        <SearchIcon fontSize={"large"} htmlColor={'white'} />
                    </Fab>
                    <Fab variant={'extended'} className={classes.button}>
                        <TuneIcon fontSize={"large"} htmlColor={'white'}/>
                    </Fab>
                </Container>
                <List className={classes.list}>
                    {carData.map(car => {
                        return (
                            <ListItem>
                                <Advertisement
                                    title={car.title}
                                    date={car.date}
                                    price={car.price}
                                />
                            </ListItem>
                        );
                    })}
                </List>
            </Container>
    );
}