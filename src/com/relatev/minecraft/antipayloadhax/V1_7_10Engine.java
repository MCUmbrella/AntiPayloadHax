package com.relatev.minecraft.antipayloadhax;

import com.relatev.minecraft.antipayloadhax.modules.*;

public class V1_7_10Engine {

    private static BiblioAtlasSWP biblioCraft;
    private static BuildCraft buildCraft;
    private static Furniture10 furniture10;
    private static ThermalExpansion thermalExpansion;
    private static EnderIO enderIO;
    private static ExtraCells extraCells;
    private static MatterOverdrive matterOverdrive;
    private static MineFactoryReloaded mineFactoryReloaded;
    private static Mekanism mekanism;
    private static RailCraft railCraft;
    private static CommandBlock commandBlock;
    private static OpenMods openMobs;
    private static BetterQuesting betterQuesting;
    private static DragonsRadio dragonsRadio;
    private static ThaumicHorizons thaumicHorizons;
    private static ThaumcraftAspect thaumcraftAspect;
    private static ProjRed projRed;
    private static TConstruct tConstruct;
    private static TravellersGear travellersGear;
    private static OpenModularTurrets openModularTurrets;
    private static BiblioAtlasWPT biblioAtlasWPT;
    private static BiblioMCBEdit biblioMCBEdit;
    private static BiblioSign biblioSign;
    private static Furniture14 furniture14;
    private static ThaumcraftResearch thaumcraftResearch;

    public static void init() {
        betterQuesting = new BetterQuesting();
        biblioCraft = new BiblioAtlasSWP();
        biblioAtlasWPT = new BiblioAtlasWPT();
        biblioMCBEdit = new BiblioMCBEdit();
        biblioSign = new BiblioSign();
        buildCraft = new BuildCraft();
        commandBlock = new CommandBlock();
        dragonsRadio = new DragonsRadio();
        enderIO = new EnderIO();
        extraCells = new ExtraCells();
        furniture10 = new Furniture10();
        furniture14 = new Furniture14();
        matterOverdrive = new MatterOverdrive();
        mekanism = new Mekanism();
        mineFactoryReloaded = new MineFactoryReloaded();
        openMobs = new OpenMods();
        openModularTurrets = new OpenModularTurrets();
        projRed = new ProjRed();
        railCraft = new RailCraft();
        tConstruct = new TConstruct();
        thaumcraftAspect = new ThaumcraftAspect();
        thaumcraftResearch = new ThaumcraftResearch();
        thaumicHorizons = new ThaumicHorizons();
        thermalExpansion = new ThermalExpansion();
        travellersGear = new TravellersGear();
    }
}
