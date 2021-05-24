import React, {useEffect, useState} from 'react';
import '../components/components.css';
import {
    Button, Checkbox,
    Container, Fab, FormControlLabel,
    Grid,
    makeStyles,
    MenuItem,
    Select,
    TextField,
    Typography
} from "@material-ui/core";
import PublishIcon from '@material-ui/icons/Publish';
import RoomIcon from '@material-ui/icons/Room';
import carBrands from "../data/carBrands";
import SearchIcon from "@material-ui/icons/Search";
import {getAnnouncements} from "../actions/getAnnouncements";


const useStyles = makeStyles((theme) => ({
    container: {
        display: 'flex',
        flexDirection: 'column',
        height: '100%',
        justifyContent: 'space-around'
    },
    form: {
        height: '70%',
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'space-between',
    },
    item: {
        width: '90%'
    },
    gridContainer: {
        background: 'transparent linear-gradient(180deg, #4FC7C3E0 0%, #4BBEBAE0 72%, #286462E0 100%) 0% 0% no-repeat padding-box',
        height: '80%',
        borderRadius: '2em'
    },
}));

export default function FilteringPanel(props) {
    const classes = useStyles();
    const [title, setTitle] = useState('');
    const [price, setPrice] = useState('');
    const [timeUnit, setTimeUnit] = useState('');
    const [brand, setBrand] = useState('');
    const [model, setModel] = useState('');
    const [blocked, setBlocked] = useState(false);
    const handleSubmit = (e) => {
        e.preventDefault();
        props.action1();
        getAnnouncements([
            {
                key: "rentStatus",
                operation: "=",
                value: "BLOCKED"
            }
        ]).then((response) => {
            props.action2(response.data);
        }).catch((error) => alert(error));
    }

    const changeBlocked = () => setBlocked(!blocked);
    return (
        <Container className={classes.container}>
            <form className={classes.form} onSubmit={handleSubmit}>
                <Grid container justify={'center'} className={classes.gridContainer}>
                    <Grid item xs={10} className={classes.titleField}>
                        <TextField
                            type={'text'}
                            label={'Title'}
                            className={classes.item}
                            value={title}
                            onChange={(e) => setTitle(e.target.value)}
                        />
                    </Grid>
                    <Grid item xs={5}>
                        <TextField
                            type={'text'}
                            label={'Price'}
                            className={classes.item}
                            value={price}
                            onChange={(e) => setPrice(e.target.value)}
                        />
                    </Grid>
                    <Grid item xs={5}>
                        <Select
                            displayEmpty
                            label={'TimeUnit'}
                            value={timeUnit}
                            onChange={(e) => setTimeUnit(e.target.value)}
                        >
                            <MenuItem value={"HOURS"}>
                                per hour
                            </MenuItem>
                            <MenuItem value={"DAYS"}>
                                per day
                            </MenuItem>
                        </Select>
                    </Grid>
                    <Grid item xs={5}>
                        <Select
                            displayEmpty
                            className={classes.item}
                            value={brand}
                            onChange={(e) => setBrand(e.target.value)}
                        >
                            {
                                carBrands.map(brand => {
                                    return (
                                        <MenuItem selected={false} value={brand.brand}>{brand.brand}</MenuItem>
                                    );
                                })
                            }
                        </Select>
                    </Grid>
                    <Grid item xs={5}>
                        <Select
                            displayEmpty
                            className={classes.item}
                            value={model}
                            onChange={(e) => setModel(e.target.value)}
                        >
                            {
                                carBrands[0].models.map(model => {
                                    return (
                                        <MenuItem value={model}>{model}</MenuItem>
                                    );
                                })
                            }
                        </Select>
                    </Grid>
                    <Grid item xs={5}>
                        <Button className={classes.item}>
                            Localisation
                            <RoomIcon />
                        </Button>
                    </Grid>
                    {
                        localStorage.getItem('role') === 'ROLE_ADMIN' &&
                        <Grid item xs={5}>
                            <Checkbox checked={blocked} onChange={changeBlocked} />
                            <Typography>
                                Blocked
                            </Typography>
                        </Grid>
                    }
                </Grid>
                <Fab variant={'extended'} className={classes.button} type={"submit"}>
                    Search
                    <SearchIcon fontSize={"large"}/>
                </Fab>
            </form>
        </Container>
    );
}