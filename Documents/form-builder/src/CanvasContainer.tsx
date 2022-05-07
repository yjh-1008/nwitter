import React, {useCallback, useRef, useState} from 'react';
import Toolbar from './Toolbar';
export const CanvasContext = React.createContext<ICanvasContext>({});

export interface ICanvasData {
    component?: string;
    id?: string;
    position?: { top: number; left: number };
    dimension?: { width: string; height: string };
    content?: string;
    type: string;
  }
  
  export interface ICanvasComponent {
    position?: { top: number; left: number };
    dimension?: { width: string; height: string };
    content?: string;
    id?: string;
    type: string;
    isReadOnly?: boolean;
  }
export interface ICanvasContext {
    state?: {
      canvasData: ICanvasData[];
      activeSelection: Set<string>;
      enableQuillToolbar: boolean;
    };
    actions?: {
      setCanvasData: React.Dispatch<React.SetStateAction<ICanvasData[]>>;
      setActiveSelection: React.Dispatch<React.SetStateAction<Set<string>>>;
      updateCanvasData: (data: Partial<ICanvasComponent>) => void;
      addElement: (type: string) => void;
      setEnableQuillToolbar: (state: boolean) => void;
    };
  }
  const getInitialData = (data: any[], type: string = "TEXT") => {
    return {
      type: type,
      id: `${type}__${Date.now()}__${data.length}`,
      position: {
        top: 100,
        left: 100
      },
      dimension: {
        width: "150",
        height: type === "TEXT" ? "50" : "150"
      },
      content: type === "TEXT" ? "Sample Text" : ""
    };
  };
const CanvasContainer=()=>{

    const [canvasData, setCanvasData] = useState<ICanvasData[]>([]);
    const [activeSelection, setActiveSelection] = useState<Set<string>>(
        new Set()
    );
    const [enableQuillToolbar, setEnableQuillToolbar] = useState<boolean>(false);

    const containerRef = useRef<HTMLDivElement>(null);

    const updateCanvasData = (data: Partial<ICanvasComponent>) => {
        const currentDataIndex =
          canvasData.findIndex((canvas) => canvas.id === data.id) ?? -1;
        const updatedData = { ...canvasData?.[currentDataIndex], ...data };
        canvasData.splice(currentDataIndex, 1, updatedData);
        setCanvasData([...(canvasData || [])]);
      };
    
      const addElement = (type: string) => {
        const defaultData = getInitialData(canvasData, type);
        setCanvasData([...canvasData, { ...defaultData, type: type ?? "TEXT" }]);
        activeSelection.clear();
        activeSelection.add(defaultData.id);
        setActiveSelection(new Set(activeSelection));
      };

    const context: ICanvasContext = {
      actions: {
        setCanvasData,
        setActiveSelection,
        updateCanvasData,
        addElement,
        setEnableQuillToolbar,
      },
      state: {
        canvasData,
        activeSelection,
        enableQuillToolbar,
      },
    };
    return(
        <div ref={containerRef}>
            <CanvasContext.Provider value={context}>
                <Toolbar isEditEnable={enableQuillToolbar}/>

            </CanvasContext.Provider>

        </div>
    );
}

export default CanvasContainer