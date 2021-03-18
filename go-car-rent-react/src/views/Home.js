import React, {Component} from 'react';
import '../components/components.css';
import {Button, Container, Grid, List, ListItem, makeStyles, Typography} from "@material-ui/core";
import Advertisement from "../components/Advertisement";

const useStyles = makeStyles((theme) => ({
    list: {
        maxHeight: '80vh',
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
        borderRadius: '2em'
    }
}));

export default function Home() {
    const classes = useStyles();
    return (
        <Grid item xs={8}>
            <Container className={classes.container}>
                <Container className={classes.buttonContainer}>
                    <Button className={classes.button}>Add</Button>
                    <Button className={classes.button}>Add</Button>
                </Container>
                <List className={classes.list}>
                    <ListItem>
                        <Advertisement
                            title={"Ford Transit Transit Transit Transit Transit Transit Transit"}
                            date={"2020-02-01"}
                            price={"200 eur/day"}
                        />
                    </ListItem>
                    <ListItem>
                        <Advertisement
                            title={"Ford Transit"}
                            date={"2020-02-01"}
                            price={"200 eur/day"}
                        />
                    </ListItem>
                    <ListItem>
                        <Advertisement
                            title={"Ford Transit"}
                            date={"2020-02-01"}
                            price={"200 eur/day"}
                        />
                    </ListItem>
                    <ListItem>
                        <Advertisement
                            title={"Ford Transit"}
                            date={"2020-02-01"}
                            price={"200 eur/day"}
                        />
                    </ListItem>
                    <ListItem>
                        <Advertisement
                            title={"Ford Transit"}
                            date={"2020-02-01"}
                            price={"200 eur/day"}
                        />
                    </ListItem>
                    <ListItem>
                        <Advertisement
                            title={"Ford Transit"}
                            date={"2020-02-01"}
                            price={"200 eur/day"}
                        />
                    </ListItem>
                    <ListItem>
                        <Advertisement
                            title={"Ford Transit"}
                            date={"2020-02-01"}
                            price={"200 eur/day"}
                        />
                    </ListItem>
                    <ListItem>
                        <Advertisement
                            title={"Ford Transit"}
                            date={"2020-02-01"}
                            price={"200 eur/day"}
                        />
                    </ListItem>
                    <ListItem>
                        <Advertisement
                            title={"Ford Transit"}
                            date={"2020-02-01"}
                            price={"200 eur/day"}
                        />
                    </ListItem>
                </List>
            </Container>
        </Grid>
    );
}